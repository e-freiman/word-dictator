package com.lll.dbengine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

import rx.Observable;

/**
 * Created by Freyman on 27.11.2015.
 */
public class DbEngineImpl extends SQLiteOpenHelper implements BaseColumns, DbEngine<String, String> {
    private static final String DATABASE_NAME = "worddictator.db";
    private static final int DATABASE_VERSION = 1;

    //TABLE_KEYS
    private static final String TABLE_KEYS = "table_keys";

    private static final String TABLE_KEYS_TEXT = "text_key";
    private static final String TABLE_KEYS_LEVEL = "level";

    private static final String DROP_TABLE_KEYS_SCRIPT = "DROP TABLE IF EXISTS " + TABLE_KEYS;
    private static final String CREATE_TABLE_KEYS_SCRIPT = "CREATE TABLE " + TABLE_KEYS + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + TABLE_KEYS_TEXT + " text not null, "
            + TABLE_KEYS_LEVEL + " integer"
            + ");";

    //TABLE_VALUES
    private static final String TABLE_VALUES = "table_values";

    private static final String TABLE_VALUES_TEXT = "text_value";
    private static final String TABLE_VALUES_ASKED = "asked";
    private static final String TABLE_VALUES_ANSWERED = "answered";
    private static final String TABLE_VALUES_FLAGS = "flags";

    private static final String DROP_TABLE_VALUES_SCRIPT = "DROP TABLE IF EXISTS " + TABLE_VALUES;
    private static final String CREATE_TABLE_VALUES_SCRIPT = "CREATE TABLE " + TABLE_VALUES + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + TABLE_VALUES_TEXT + " text not null, "
            + TABLE_VALUES_ASKED + " integer, "
            + TABLE_VALUES_ANSWERED + " integer, "
            + TABLE_VALUES_FLAGS + " integer"
            + ");";

    //TABLE_RECORDS
    private static final String TABLE_RECORDS = "table_records";

    private static final String TABLE_RECORDS_ID_KEY = "id_key";
    private static final String TABLE_RECORDS_ID_VALUE = "id_value";

    private static final String DROP_TABLE_RECORDS_SCRIPT = "DROP TABLE IF EXISTS " + TABLE_RECORDS;
    private static final String CREATE_TABLE_RECORDS_SCRIPT = "CREATE TABLE " + TABLE_VALUES + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + TABLE_RECORDS_ID_KEY + " integer, "
            + TABLE_RECORDS_ID_VALUE + " integer"
            + ");";

    public DbEngineImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void clean() {
        SQLiteDatabase db = getReadableDatabase();

        db.execSQL(DROP_TABLE_KEYS_SCRIPT);
        db.execSQL(DROP_TABLE_VALUES_SCRIPT);
        db.execSQL(DROP_TABLE_RECORDS_SCRIPT);

        onCreate(db);
    }

    private long getKeyId(DbKey<String> key) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_KEYS,
                new String[]{BaseColumns._ID},
                "WHERE " + TABLE_KEYS_TEXT + "=?",
                new String[]{key.get()},
                null, null, null, null);

        long id_key;
        if (cursor.getCount() == 0) {
            ContentValues newValue = new ContentValues();
            newValue.put(TABLE_KEYS_TEXT, key.get());
            newValue.put(TABLE_KEYS_LEVEL, key.getLevel());
            id_key = db.insert(TABLE_KEYS, null, newValue);
        } else {
            if (cursor.getCount() > 1) {
                throw new IllegalStateException(TABLE_KEYS + " contains more than one instance of '" + key.get() + "' word");
            }

            id_key = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
        }

        if (id_key < 0) {
            throw new SQLException("The key '" + key.get() + "' wasn't added into " + TABLE_KEYS);
        }

        return id_key;
    }

    private long getValueId(DbValue<String> value) {
        SQLiteDatabase db = getReadableDatabase();
        long valueId;

        Cursor cursor = db.query(TABLE_VALUES,
                new String[]{BaseColumns._ID},
                "WHERE " + TABLE_VALUES_TEXT + "=?",
                new String[]{value.get()},
                null, null, null, null);

        long id_value;
        if (cursor.getCount() == 0) {
            ContentValues newValue = new ContentValues();
            newValue.put(TABLE_VALUES_TEXT, value.get());
            newValue.put(TABLE_VALUES_ASKED, value.getAsked());
            newValue.put(TABLE_VALUES_ANSWERED, value.getAnswered());
            newValue.put(TABLE_VALUES_FLAGS, value.getFlags());
            id_value = db.insert(TABLE_VALUES, null, newValue);
        } else {
            if (cursor.getCount() > 1) {
                throw new IllegalStateException(TABLE_VALUES + " contains more that one instance of '" + value.get() + "' word");
            }

            id_value = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
        }

        if (id_value < 0) {
            throw new SQLException("The value '" + value.get() + "' wasn't added into " + TABLE_RECORDS);
        }

        return id_value;
    }

    @Override
    public void addRecord(DbRecord<String, String> dbRecord) {
        SQLiteDatabase db = getReadableDatabase();

        //Searching if key is already in table_key , add if necessary
        long keyId = getKeyId(dbRecord.getKey());

        //Iterating through values
        for (DbValue<String> value : dbRecord.getValues()) {

            //Searching value is already in table_values, add if necessary
            long valueId = getValueId(value);

            //Checking that there isn't record with the same id_key and id_value in table_records
            Cursor cursor = db.query(TABLE_VALUES,
                    new String[]{BaseColumns._ID},
                    "WHERE " + TABLE_RECORDS_ID_KEY + "=? AND " + TABLE_RECORDS_ID_VALUE + "=?",
                    new String[]{Long.toString(keyId), Long.toString(valueId)},
                    null, null, null, null);

            if (cursor.getCount() != 0) {
                throw new IllegalArgumentException(TABLE_RECORDS + " has already had the row that contains key = '"
                        + dbRecord.getKey().get() + "' and value = '" + value.get() + "'. The ID of this row is "
                        + cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)));
            }

            //Adding a record
            ContentValues newValue = new ContentValues();
            newValue.put(TABLE_RECORDS_ID_KEY, keyId);
            newValue.put(TABLE_RECORDS_ID_VALUE, valueId);
            long id_record = db.insert(TABLE_RECORDS, null, newValue);

            if (id_record < 0) {
                throw new SQLException("The record with key = '" + dbRecord.getKey().get()
                        + "' and value = '" + value.get() + "' wasn't added into " + TABLE_RECORDS);
            }
        }
    }

    @Override
    public void updateRecord(DbRecord<String, String> dbRecord) {

    }

    @Override
    public Observable<List<DbRecord<String, String>>> getRecords(String sqlQuery) {
        return null;
    }

    @Override
    public void addJSON(String filename) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KEYS_SCRIPT);
        db.execSQL(CREATE_TABLE_VALUES_SCRIPT);
        db.execSQL(CREATE_TABLE_RECORDS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_KEYS_SCRIPT);
        db.execSQL(DROP_TABLE_VALUES_SCRIPT);
        db.execSQL(DROP_TABLE_RECORDS_SCRIPT);
        onCreate(db);
    }
}

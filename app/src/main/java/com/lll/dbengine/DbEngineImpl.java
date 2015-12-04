package com.lll.dbengine;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

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


    @Override
    public boolean addRecord(DbRecord<String, String> dbRecord) {
        SQLiteDatabase db = getReadableDatabase();

        //Searching if key is in table_key already, add if necessary
        //int id_key;

        //Iterating through values
        for (DbValue<String> value : dbRecord.getValues()) {

            //Searching value in table_values, add if necessary
            int id_value;

            //Checking that there isn't record with the same id_key and id_value in table_records

            //Adding a record

            //Example:
            ContentValues newValue = new ContentValues();
            newValue.put(TABLE_KEYS_TEXT, "Cat");
            newValue.put(TABLE_KEYS_LEVEL, "1");
            long id = db.insert(TABLE_KEYS, null, newValue);
            Log.d("ABC", String.format("id = %d", id));
        }

        return true;
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

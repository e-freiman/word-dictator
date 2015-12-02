package com.lll.dbengine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

import rx.Observable;

/**
 * Created by Freyman on 27.11.2015.
 */
public class DbEngineImpl extends SQLiteOpenHelper implements DbEngine<String, String>  {
    private static final String DATABASE_NAME = "worddictator.db";
    private static final int DATABASE_VERSION = 1;

    public DbEngineImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void createEmpty() {

    }

    @Override
    public void addRecord(DbRecord<String, String> dbRecord) {

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
        //db.execSQL(CREATE_KEYS_TABLE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

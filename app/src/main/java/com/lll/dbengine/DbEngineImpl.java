package com.lll.dbengine;

import java.util.List;

import rx.Observable;

/**
 * Created by Freyman on 27.11.2015.
 */
public class DbEngineImpl implements DbEngine<String, String> {
    @Override
    public void createEmpty() {

    }

    @Override
    public void addRecord(DbRecord<String, String> dbRecord) {

    }

    @Override
    public void addJSON(String filename) {

    }

    @Override
    public void setFlags(int id, int flags) {

    }

    @Override
    public void incAsked(int id) {

    }

    @Override
    public void incAnswered(int id) {

    }

    @Override
    public Observable<List<DbRecord<String, String>>> getRecords(String sqlQuery) {
        return null;
    }
}

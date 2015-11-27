package com.lll.dbengine;

import java.util.List;

import rx.Observable;

/**
 * Created by Freyman on 27.11.2015.
 * Interface for manipulating with database.
 * Any work with database goes through it
 */
public interface DbEngine<Key, Value> {
    void createEmpty();

    void addRecord(DbRecord<Key, Value> dbRecord);
    void addJSON(String filename);

    void setFlags(int id, int flags);
    void incAsked(int id);
    void incAnswered(int id);

    Observable<List<DbRecord<Key, Value>>> getRecords(String sqlQuery);
}

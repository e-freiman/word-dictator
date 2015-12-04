package com.lll.dbengine;

import java.util.List;

import rx.Observable;

/**
 * Created by Freyman on 27.11.2015.
 * Interface for manipulating with database.
 * Any work with database goes through it
 */
public interface DbEngine<Key, Value> {
    void clean();
    void close();

    /**
     *
     * @param dbRecord record to add
     * @return true if value was added successfully, false if the record includes
     * link between key and value that already present in the database or some other
     * problems
     */
    boolean addRecord(DbRecord<Key, Value> dbRecord);
    void updateRecord(DbRecord<Key, Value> dbRecord);
    Observable<List<DbRecord<Key, Value>>> getRecords(String sqlQuery);
    void addJSON(String filename);
}

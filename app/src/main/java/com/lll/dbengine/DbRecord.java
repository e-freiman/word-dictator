package com.lll.dbengine;

import java.util.List;

/**
 * Created by Freyman on 27.11.2015.
 * Represents a record in database which corresponds to link
 * between a key and the set of values (for example English phrase and Russian translations)
 */
public final class DbRecord<Key, Value> {

    private DbKey<Key> mKey;
    private List<DbValue<Value>> mValues;

    //Id is set only by database through package visible constructor
    private int mId;

    /**
     * Constructor for creating DbRecord by the user, for example in order
     * to put it into database
     *
     * @param key
     * @param values
     */
    public DbRecord(DbKey<Key> key, List<DbValue<Value>> values) {
        mKey = key;
        mValues = values;
    }

    /**
     * Constructor for creating a record when it is returned from database
     *
     * @param id
     * @param key
     * @param values
     */
    DbRecord(int id, DbKey<Key> key, List<DbValue<Value>> values) {
        mId = id;
        mKey = key;
        mValues = values;
    }

    public DbKey<Key> getKey() {
        return mKey;
    }

    public List<DbValue<Value>> getValues() {
        return mValues;
    }

    public int getId() {
        return mId;
    }
}

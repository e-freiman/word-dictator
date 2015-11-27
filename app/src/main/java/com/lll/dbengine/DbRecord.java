package com.lll.dbengine;

/**
 * Created by Freyman on 27.11.2015.
 * Represents a record in database which corresponds to link
 * between key and value (for example English phrase and Russian translation)
 */
public final class DbRecord<Key, Value> {

    private Key mKey;
    private Value mValue;
    private int mLevel, mAsked, mAnswered, mFlags;

    //Id is set only by database through package visible constructor
    private int mId;

    /**
     * Constructor for creating DbRecord manually, for example in order
     * to put it into database
     * @param key
     * @param value
     * @param level
     */
    public DbRecord(Key key, Value value, int level) {
        mKey = key;
        mValue = value;
        mLevel = level;
    }

    /**
     * Constructor for creating a record when it is returned from database
     * @param id
     * @param key
     * @param value
     * @param level
     * @param asked
     * @param answered
     */
    DbRecord(int id, Key key, Value value, int level, int asked, int answered) {
        mId = id;
        mKey = key;
        mValue = value;
        mLevel = level;
        mAsked = asked;
        mAnswered = answered;
    }

    public int getId() {
        return mId;
    }

    public int getAsked() {
        return mAsked;
    }

    public Key getKey() {
        return mKey;
    }

    public Value getValue() {
        return mValue;
    }

    public int getLevel() {
        return mLevel;
    }

    public int getAnswered() {
        return mAnswered;
    }

    public int getFlags() {
        return mFlags;
    }
}

package com.lll.dbengine;

/**
 * Created by Freyman on 02.12.2015.
 */
public class DbKey<T> {
    private T mKey;
    private int mLevel;

    public DbKey(T key, int level) {
        mKey = key;
        mLevel = level;
    }

    public T get() {
        return mKey;
    }

    public int getLevel() {
        return mLevel;
    }
}

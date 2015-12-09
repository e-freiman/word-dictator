package com.lll.dbengine;

/**
 * Created by Freyman on 02.12.2015.
 */
public class DbValue<T> {

    private T mValue;
    private int mAsked, mAnswered, mFlags;

    public DbValue(T value, int asked, int answered, int flags) {
        mValue = value;
        mAsked = asked;
        mAnswered = answered;
        mFlags = flags;
    }

    public T get() {
        return mValue;
    }

    public int getAsked() {
        return mAsked;
    }

    public int getAnswered() {
        return mAnswered;
    }

    public int getFlags() {
        return mFlags;
    }
}

package com.lll.worddictator;

import com.lll.dbengine.DbKey;

/**
 * Created by Freyman on 11.12.2015.
 */
public class EngWord extends DbKey<String> {
    public EngWord(String word, int level) {
        super(word, level);
    }
}

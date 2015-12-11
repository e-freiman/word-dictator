package com.lll.worddictator;

import com.lll.dbengine.DbValue;

/**
 * Created by Freyman on 11.12.2015.
 */
public class RusWord extends DbValue<String> {
    public RusWord(String word, int asked, int answered, int flags) {
        super(word, asked, answered, flags);
    }

    public RusWord(String word, int flags) {
        super(word, 0, 0, flags);
    }
}

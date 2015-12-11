package com.lll.worddictator;

import com.lll.dbengine.DbRecord;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Freyman on 11.12.2015.
 */
public class Translation extends DbRecord<String, String> {

    private static List<RusWord> getRusWordsList(String... rusWords) {
        List<RusWord> rusWordsList = new LinkedList<>();
        for (String arg : rusWords) {
            RusWord rusWord = new RusWord(arg, 0, 0, 0);
            rusWordsList.add(rusWord);
        }
        return rusWordsList;
    }

    public Translation(String engWord, int level, String... rusWords) {
        super(new EngWord(engWord, level), getRusWordsList(rusWords));
    }

    public Translation(DbRecord<String, String> record) {
        //THE PROJECT HAS BEEN KILLED BY GREGORY.
        //Long days Word Dictator
        //Here curtains falls, light is lit, applause
    }
}

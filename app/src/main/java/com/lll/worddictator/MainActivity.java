package com.lll.worddictator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.lll.dbengine.DbEngine;
import com.lll.dbengine.DbEngineImpl;
import com.lll.dbengine.DbKey;
import com.lll.dbengine.DbRecord;
import com.lll.dbengine.DbValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    private <T extends Number> void f(List<T> x) {
        Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_SHORT).show();
    }

    private void createSampleDb(DbEngine<String, String> engine) {
        engine.clean();
        engine.addRecord(new Translation("cat", 1, "кошка"));
        engine.addRecord(new Translation("dog", 1, "собака"));
        engine.addRecord(new Translation("fish", 1, "рыба"));
        engine.addRecord(new Translation("tiger", 1, "тигр"));
        engine.addRecord(new Translation("creature", 2, "существо", "создание"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        f(new ArrayList<Number>());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxView.clicks(findViewById(R.id.rxtest_button)).subscribe(notification -> {
            Observable.just("Hello, world!")
                    .map(s -> s + " -ABC")
                    .subscribe(s -> Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show());
        });

        RxView.clicks(findViewById(R.id.dbtest_button)).subscribe(notification -> {
            DbEngine<String, String> engine = new DbEngineImpl(getApplicationContext());
            createSampleDb(engine);
        });
    }
}

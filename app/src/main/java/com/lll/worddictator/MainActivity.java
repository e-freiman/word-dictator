package com.lll.worddictator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.lll.dbengine.DbEngine;
import com.lll.dbengine.DbEngineImpl;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxView.clicks(findViewById(R.id.rxtest_button)).subscribe(notification -> {
            Observable.just("Hello, world!")
                    .map(s -> s + " -ABC")
                    .subscribe(s -> Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show());
        });

        RxView.clicks(findViewById(R.id.dbtest_button)).subscribe(notification -> {
            DbEngine<String, String> engine = new DbEngineImpl();
            engine.createEmpty();
        });
    }
}

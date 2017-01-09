package com.wfc.test7.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wfc.test7.MyApp;


public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
//        ButterKnife.bind(this);
        setupApp((MyApp) getApplication());
    }

    protected abstract void setupApp(MyApp app);
    protected abstract int layoutId();

}
package com.wfc.test7.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wfc.test7.MyApp;

import java.util.LinkedHashMap;
import java.util.Map;


public abstract class BaseActivity extends AppCompatActivity {


    protected Map<String, String> params = new LinkedHashMap<>();
    
    protected abstract void setupApp(MyApp app);

    protected abstract int layoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
//        ButterKnife.bind(this);
        setupApp((MyApp) getApplication());
    }

}
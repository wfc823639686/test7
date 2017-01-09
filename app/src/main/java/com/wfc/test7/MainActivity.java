package com.wfc.test7;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wfc.test7.apis.JobService;
import com.wfc.test7.base.BaseActivity;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Subscriber;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    JobService jobService;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "3");
        jobService.getJobInfo(params)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext " + s);
                    }
                });

    }

    @Override
    protected void setupApp(MyApp app) {
        app.getNetComponent().inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}

package com.wfc.test7.mvp.jobinfo;

import android.os.Bundle;

import com.wfc.test7.MyApp;
import com.wfc.test7.R;
import com.wfc.test7.base.BaseActivity;
import com.wfc.test7.components.NetComponent;

import javax.inject.Inject;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public class JobInfoActivity extends BaseActivity {

    NetComponent netComponent;

    @Inject
    JobInfoPresenter jobInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JobInfoFragment jobInfoFragment = JobInfoFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, jobInfoFragment)
                .commit();
        DaggerJobInfoComponent.builder()
                .jobInfoPresenterModule(new JobInfoPresenterModule(jobInfoFragment))
                .netComponent(netComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void setupApp(MyApp app) {
        netComponent = app.getNetComponent();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_job_info;
    }
}

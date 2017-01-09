package com.wfc.test7.mvp.joblist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wfc.test7.MyApp;
import com.wfc.test7.R;
import com.wfc.test7.base.BaseActivity;
import com.wfc.test7.components.NetComponent;

import javax.inject.Inject;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public class JobListActivity extends BaseActivity {

    @Inject
    JobListPresenter jobListPresenter;
    NetComponent netComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JobListFragment jobListFragment = JobListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_content, jobListFragment)
                .commit();
        DaggerJobListComponent.builder()
                .jobListModule(new JobListModule(jobListFragment))
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
        return R.layout.activity_job_list;
    }


}

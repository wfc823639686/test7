package com.wfc.test7.mvp.joblist;

import com.wfc.test7.base.FragmentScoped;
import com.wfc.test7.components.NetComponent;

import dagger.Component;

/**
 * Created by wangfengchen on 2017/1/9.
 */
@FragmentScoped
@Component(modules = JobListPresenterModule.class, dependencies = NetComponent.class)
public interface JobListComponent {

    void inject(JobListActivity activity);
}

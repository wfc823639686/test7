package com.wfc.test7.mvp.jobinfo;

import com.wfc.test7.base.FragmentScoped;
import com.wfc.test7.components.NetComponent;

import dagger.Component;

/**
 * Created by wangfengchen on 2017/1/9.
 */
@FragmentScoped
@Component(modules = JobInfoPresenterModule.class, dependencies = NetComponent.class)
public interface JobInfoComponent {

    void inject(JobInfoActivity activity);
}

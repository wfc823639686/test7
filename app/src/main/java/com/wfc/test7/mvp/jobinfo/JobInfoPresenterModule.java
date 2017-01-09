package com.wfc.test7.mvp.jobinfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangfengchen on 2017/1/9.
 */

@Module
public class JobInfoPresenterModule {

    private final JobInfoContract.View mView;

    public JobInfoPresenterModule(JobInfoContract.View view) {
        mView = view;
    }

    @Provides
    public JobInfoContract.View provideJObInfoContractView() {
        return mView;
    }
}

package com.wfc.test7.mvp.joblist;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangfengchen on 2017/1/9.
 */

@Module
public class JobListPresenterModule {

    private final JobListContract.View mView;

    public JobListPresenterModule(JobListContract.View view) {
        mView = view;
    }

    @Provides
    public JobListContract.View provideJobListContractView() {
        return mView;
    }
}

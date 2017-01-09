package com.wfc.test7.mvp.jobinfo;

import com.wfc.test7.apis.JobService;
import com.wfc.test7.beans.JobInfoResult;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by wangfengchen on 2017/1/9.
 */

public class JobInfoPresenter implements JobInfoContract.Presenter {

    JobInfoContract.View mView;

    JobService jobService;

    @Inject
    public JobInfoPresenter(JobInfoContract.View view, JobService jobService) {
        mView = view;
        this.jobService = jobService;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void getJobInfo() {
        mView.showLoading();
        jobService.getJobInfo(mView.jobInfoParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobInfoResult>() {
                    @Override
                    public void onCompleted() {
                        mView.showContent();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        mView.showError(throwable, null);
                    }

                    @Override
                    public void onNext(JobInfoResult s) {
                        mView.setData(s);
                    }
                });
    }

    @Override
    public void start() {

    }
}

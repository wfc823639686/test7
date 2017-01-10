package com.wfc.test7.mvp.jobinfo;

import com.trello.rxlifecycle.ActivityEvent;
import com.wfc.test7.apis.JobService;
import com.wfc.test7.base.BaseActivity;
import com.wfc.test7.base.BaseFragment;
import com.wfc.test7.beans.JobInfoResult;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by wangfengchen on 2017/1/9.
 */

public class JobInfoPresenter implements JobInfoContract.Presenter {

    private JobInfoContract.View mView;

    private JobService jobService;

    private BaseFragment mTarget;

    @Inject
    public JobInfoPresenter(JobInfoContract.View view,
                            JobService jobService) {
        mView = view;
        mTarget = (BaseFragment) mView;
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
                .compose(mTarget.<JobInfoResult>bindUntilEvent(ActivityEvent.DESTROY))
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

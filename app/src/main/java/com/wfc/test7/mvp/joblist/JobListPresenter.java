package com.wfc.test7.mvp.joblist;

import android.util.Log;

import com.wfc.test7.apis.JobService;
import com.wfc.test7.beans.JobListResult;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public class JobListPresenter implements JobListContract.Presenter {

    private static final String TAG = "JobListPresenter";
    JobListContract.View mView;
    JobService jobService;

    @Inject
    public JobListPresenter(JobListContract.View view, JobService jobService) {
        Log.d(TAG, "new JobListPresenter");
        mView = view;
        this.jobService = jobService;
    }

    @Override
    public void start() {
        Log.d(TAG, "start");
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     * For more information, see Java Concurrency in Practice.
     */
    @Inject
    void setupListeners() {
        Log.d(TAG, "setupListeners");
        mView.setPresenter(this);
    }

    @Override
    public void getJobList(final int vt) {
        mView.viewStatus(vt, true);
        jobService.getJobList(mView.jobListParams())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JobListResult>() {
                    @Override
                    public void onCompleted() {
                        mView.viewStatus(vt, false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.viewStatus(vt, false);
                    }

                    @Override
                    public void onNext(JobListResult s) {
                        Log.d(TAG, "result " + s);
                        mView.setData(vt, s);
                    }
                });
    }
}

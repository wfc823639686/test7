package com.wfc.test7.mvp.joblist;

import android.util.Log;

import com.wfc.test7.apis.JobService;
import com.wfc.test7.beans.JobListResult;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Inject
    void setupListeners1() {
        Log.d(TAG, "setupListeners1");
    }

    @Override
    public void getJobList(final int vt) {
        mView.viewStatus(vt, true);
        jobService.getJobList(mView.jobListParams())
                .enqueue(new Callback<JobListResult>() {
                    @Override
                    public void onResponse(Call<JobListResult> call, Response<JobListResult> response) {
                        mView.viewStatus(vt, false);
                        mView.setData(vt, response.body());
                    }

                    @Override
                    public void onFailure(Call<JobListResult> call, Throwable t) {
                        mView.viewStatus(vt, false);
                    }
                });
    }
}

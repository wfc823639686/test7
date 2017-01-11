package com.wfc.test7.mvp.jobinfo;

import android.util.Log;

import com.wfc.test7.apis.JobService;
import com.wfc.test7.base.BaseFragment;
import com.wfc.test7.beans.JobInfoResult;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobInfoPresenter implements JobInfoContract.Presenter {

    private static final String TAG = "JobInfoPresenter";

    private JobInfoContract.View mView;

    private JobService jobService;

    @Inject
    public JobInfoPresenter(JobInfoContract.View view,
                            JobService jobService) {
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
        jobService.getJobInfo(mView.jobInfoParams()).enqueue(new Callback<JobInfoResult>() {
            @Override
            public void onResponse(Call<JobInfoResult> call, Response<JobInfoResult> response) {
                mView.setData(response.body());
                mView.showContent();
            }

            @Override
            public void onFailure(Call<JobInfoResult> call, Throwable t) {
                mView.showError(t, null);
            }
        });
    }

    @Override
    public void postJob() {
        jobService.postJob(mView.postJobParams()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, "result " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "result " + t.getMessage());
            }
        });
    }

    @Override
    public void start() {

    }
}

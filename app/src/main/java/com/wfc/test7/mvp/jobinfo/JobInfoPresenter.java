package com.wfc.test7.mvp.jobinfo;

import com.wfc.test7.apis.JobService;
import com.wfc.test7.base.BaseFragment;
import com.wfc.test7.beans.JobInfoResult;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobInfoPresenter implements JobInfoContract.Presenter {

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
    public void start() {

    }
}

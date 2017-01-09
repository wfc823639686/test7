package com.wfc.test7.mvp.jobinfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfc.test7.R;
import com.wfc.test7.base.BaseFragment;
import com.wfc.test7.base.MvpLceFragment;
import com.wfc.test7.beans.JobInfoResult;

import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public class JobInfoFragment extends MvpLceFragment implements JobInfoContract.View {

    private static final String TAG = "JobInfoFragment";
    JobInfoContract.Presenter mPresenter;

    public static JobInfoFragment newInstance() {
        return new JobInfoFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getJobInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_info;
    }

    @Override
    public void setData(JobInfoResult data) {
        Log.d(TAG, "data" + data.msg);
    }

    @Override
    public Map<String, String> jobInfoParams() {
        params.put("id", "3");
        params.put("enterpriseId", "4");
        return params;
    }

    @Override
    public void setPresenter(JobInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

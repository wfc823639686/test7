package com.wfc.test7.mvp.joblist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import com.wfc.test7.R;
import com.wfc.test7.base.MvpRefreshLoadingFragment;
import com.wfc.test7.beans.Job;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public class JobListFragment extends MvpRefreshLoadingFragment<Job>
        implements JobListContract.View {

    private static final String TAG = "JobListFragment";

    JobListContract.Presenter mPresenter;


    public static JobListFragment newInstance() {
        return new JobListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getJobList(0);
    }

    @Override
    protected void setAdapter() {
        mAdapter = new JobListAdapter(getActivity(), R.layout.item_job, dataList);
    }

    @Override
    public void setPresenter(JobListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_job_list;
    }

    @Override
    public void setData(int vt, List<Job> data) {
        if (vt == 0) {
            dataList.clear();
        }
        dataList.addAll(data);
        mLoadMoreWrapper.notifyDataSetChanged();
    }

    @Override
    public Map<String, String> listParams() {
        return listParams;
    }

    @Override
    public int last() {
        if(dataList!=null && !dataList.isEmpty()) {
            return dataList.get(dataList.size()-1).id;
        }
        return 0;
    }

    @Override
    public void onRefresh() {
        mPresenter.getJobList(0);
    }

    @Override
    protected void onLoadMore() {
        mPresenter.getJobList(1);
    }
}

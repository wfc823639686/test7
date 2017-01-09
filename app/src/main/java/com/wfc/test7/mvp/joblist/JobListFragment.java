package com.wfc.test7.mvp.joblist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wfc.test7.R;
import com.wfc.test7.base.BaseFragment;
import com.wfc.test7.beans.Job;
import com.wfc.test7.beans.JobListResult;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public class JobListFragment extends BaseFragment implements JobListContract.View {

    private static final String TAG = "JobListFragment";
    JobListContract.Presenter mPresenter;

    SwipeRefreshLayout mSwipeView;

    RecyclerView mRecyclerView;

    LoadMoreWrapper<Job> loadMoreWrapper;

    JobListAdapter jobListAdapter;

    Map<String, String> jobListParams = new LinkedHashMap<>();

    List<Job> jobs = new ArrayList<>();

    public static JobListFragment newInstance() {
        return new JobListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        jobListAdapter = new JobListAdapter(getActivity(), R.layout.item_job, jobs);
        HeaderAndFooterWrapper<Job> headerAndFooterWrapper = new HeaderAndFooterWrapper<>(jobListAdapter);
        loadMoreWrapper = new LoadMoreWrapper<>(headerAndFooterWrapper);
        mRecyclerView.setAdapter(loadMoreWrapper);
        mPresenter.getJobList(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(JobListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_job_list, container, false);
    }

    @Override
    public void setData(int vt, JobListResult data) {
        if(data!=null && data.status==1) {
            if(vt==0) {
                jobs.clear();
            }
            jobs.addAll(data.results);
            jobListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Map<String, String> jobListParams() {
        jobListParams.put("id", "1");
        return jobListParams;
    }

    @Override
    public void viewStatus(int vt, boolean r) {
        switch (vt) {
            case 0:
                Log.d(TAG, "refresh " + r);
                mSwipeView.setRefreshing(r);
                break;
            case 1:
                Log.d(TAG, "load more " + r);
                break;
        }
    }
}

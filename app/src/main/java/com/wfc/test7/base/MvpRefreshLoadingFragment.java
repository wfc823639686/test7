package com.wfc.test7.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wfc.test7.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public abstract class MvpRefreshLoadingFragment<M> extends BaseFragment implements MvpRefreshLoadingView<M> {

    private static final String TAG = "MvpRefreshLoadingFrag";

    protected List<M> dataList = new ArrayList<>();

    protected SwipeRefreshLayout mSwipeView;

    protected RecyclerView mRecyclerView;

    protected LoadMoreWrapper<M> mLoadMoreWrapper;

    protected CommonAdapter<M> mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipeView);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setupRecyclerView();
    }

    protected void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        HeaderAndFooterWrapper<M> headerAndFooterWrapper = new HeaderAndFooterWrapper<>(mAdapter);
        mLoadMoreWrapper = new LoadMoreWrapper<>(headerAndFooterWrapper);
        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }

    protected abstract void setAdapter();

    @Override
    public void onRefreshLoading(int vt, boolean r) {
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

    @Override
    public void showError(Throwable e, Bundle bundle) {

    }
}

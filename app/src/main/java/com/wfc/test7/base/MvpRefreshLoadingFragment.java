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
import com.wfc.test7.view.recycler.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public abstract class MvpRefreshLoadingFragment<M> extends BaseFragment
        implements MvpRefreshLoadingView<M>, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MvpRefreshLoadingFrag";

    protected List<M> dataList = new ArrayList<>();

    protected SwipeRefreshLayout mSwipeView;

    protected RecyclerView mRecyclerView;

    protected LoadMoreWrapper mLoadMoreWrapper;

    protected CommonAdapter<M> mAdapter;

    protected Map<String, String> listParams = new LinkedHashMap<>();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeView = (SwipeRefreshLayout) view.findViewById(R.id.swipeView);
        mSwipeView.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setupRecyclerView();
    }

    protected void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        HeaderAndFooterWrapper<M> headerAndFooterWrapper = new HeaderAndFooterWrapper<>(mAdapter);
        mLoadMoreWrapper = new LoadMoreWrapper(getActivity(), headerAndFooterWrapper);
        mLoadMoreWrapper.setOnLoadListener(new LoadMoreWrapper.OnLoadListener() {
            @Override
            public void onRetry() {
                loadRetry();
            }

            @Override
            public void onLoadMore() {
                MvpRefreshLoadingFragment.this.onLoadMore();
            }
        });
        mRecyclerView.setAdapter(mLoadMoreWrapper);
    }

    protected abstract void setAdapter();

    protected void loadRetry() {

    }

    protected void onLoadMore() {

    }

    @Override
    public void onRefreshLoading(int vt, boolean r) {
        switch (vt) {
            case 0:
                Log.d(TAG, "refresh " + r);
                mSwipeView.setRefreshing(r);
                break;
            case 1:
                Log.d(TAG, "load more " + r);
//                if(r) {
//                    mLoadMoreWrapper.showLoadMore();
//                } else {
//                    mLoadMoreWrapper.disableLoadMore();
//                }
                break;
        }
    }

    @Override
    public void showError(Throwable e, Bundle bundle) {

    }
}

package com.wfc.test7.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wfc.test7.R;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public abstract class MvpLceFragment extends BaseFragment implements MvpLceView {

    View loadingView, contentView, errorView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingView = view.findViewById(R.id.loadingView);
        contentView = view.findViewById(R.id.contentView);
        errorView = view.findViewById(R.id.errorView);
    }

    @Override
    public void showContent() {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e, Bundle data) {
        loadingView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }
}

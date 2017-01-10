package com.wfc.test7.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public abstract class BaseFragment extends Fragment {

    View rootView;

    protected Map<String, String> params = new LinkedHashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = getRootView(inflater, container, savedInstanceState);
        }
        return rootView;
    }

    protected View getRootView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    protected abstract int getLayoutId();

}

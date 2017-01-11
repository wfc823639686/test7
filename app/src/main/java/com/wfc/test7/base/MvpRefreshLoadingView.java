package com.wfc.test7.base;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

public interface MvpRefreshLoadingView<M> {

    void setData(int vt, List<M> data);

    void onRefreshLoading(int vt, boolean r);

    Map<String, String> listParams();

    void showError(Throwable e, Bundle bundle);
}

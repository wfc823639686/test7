package com.wfc.test7.base;


import android.os.Bundle;

public interface MvpLceView {

    void showError(Throwable e, Bundle data);

    void showContent();

    void showLoading();

}

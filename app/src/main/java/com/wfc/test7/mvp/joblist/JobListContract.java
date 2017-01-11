package com.wfc.test7.mvp.joblist;

import com.wfc.test7.base.BasePresenter;
import com.wfc.test7.base.BaseView;
import com.wfc.test7.base.MvpRefreshLoadingView;
import com.wfc.test7.beans.Job;

public interface JobListContract {

    interface View extends BaseView<Presenter>, MvpRefreshLoadingView<Job> {

    }

    interface Presenter extends BasePresenter {
        /**
         * 获取职位列表
         * @param vt 0:刷新 1:加载
         */
        void getJobList(int vt);
    }
}

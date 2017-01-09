package com.wfc.test7.mvp.joblist;

import com.wfc.test7.base.BasePresenter;
import com.wfc.test7.base.BaseView;
import com.wfc.test7.beans.JobListResult;

import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public interface JobListContract {

    interface View extends BaseView<Presenter> {
        void setData(int vt, JobListResult data);
        Map<String, String> jobListParams();
        void viewStatus(int vt, boolean r);
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取职位列表
         * @param vt 0:刷新 1:加载
         */
        void getJobList(int vt);
    }
}

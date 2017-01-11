package com.wfc.test7.mvp.jobinfo;

import com.wfc.test7.base.BasePresenter;
import com.wfc.test7.base.BaseView;
import com.wfc.test7.base.MvpLceView;
import com.wfc.test7.beans.JobInfoResult;
import com.wfc.test7.beans.JobListResult;

import java.util.Map;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public interface JobInfoContract {

    interface View extends BaseView<Presenter>, MvpLceView {
        void setData(JobInfoResult data);
        Map<String, String> jobInfoParams();
        Map<String, String> postJobParams();
    }

    interface Presenter extends BasePresenter {

        void getJobInfo();

        void postJob();
    }
}

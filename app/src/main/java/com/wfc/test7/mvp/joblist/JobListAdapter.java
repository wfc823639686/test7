package com.wfc.test7.mvp.joblist;

import android.content.Context;

import com.wfc.test7.R;
import com.wfc.test7.beans.Job;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by wangfengchen on 2017/1/9.
 */

public class JobListAdapter extends CommonAdapter<Job> {

    public JobListAdapter(Context context, int layoutId, List<Job> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Job job, int position) {
        holder.setText(R.id.item_job_name, job.jobName);
    }
}

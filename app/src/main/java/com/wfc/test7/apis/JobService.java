package com.wfc.test7.apis;


import com.wfc.test7.beans.JobListResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

//定义接口
public interface JobService {
        //GET注解不可用@FormUrlEncoded，要用@Query注解引入请求参数


        @GET("api/job/getJobs.htm")
        Observable<JobListResult> getJobList(@QueryMap Map<String, String> map);

        @GET("api/job/getInfo.htm")
        Observable<String> getJobInfo(@QueryMap Map<String, String> map);

        @GET("api/enterprise/getComments.htm")
        Observable<String> getComments(@QueryMap Map<String, String> map);

        @POST("api/job/update.htm")
        @FormUrlEncoded
        Observable<String> postJob(@FieldMap Map<String, String> map);


        //POST方法没有缓存，适用于更新数据
//        @FormUrlEncoded
//        @POST("")
//        Observable<JobResult> insertJob(@Field("userName") String userName);
    }
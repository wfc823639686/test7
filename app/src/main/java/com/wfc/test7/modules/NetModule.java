package com.wfc.test7.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.preference.PreferenceManager;
import android.support.constraint.solver.Cache;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wfc.test7.apis.JobService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {


    private static final String TAG = "NetModule";
    String mBaseUrl;

    // Constructor needs one parameter to instantiate.  
    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    // Application reference must come from AppModule.class
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

//    @Provides
//    @Singleton
//    Cache provideOkHttpCache(Application application) {
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        return cache;
//    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("OK_HTTP", message);
            }
        });
        //包含header、body数据
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Log.i(TAG, "provideHttpLoggingInterceptor");
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        Log.i(TAG, "provideOkHttpClient");
        return new OkHttpClient
                .Builder()
                //FaceBook 网络调试器，可在Chrome调试网络请求，查看SharePreferences,数据库等
//                                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Log.i(TAG, "provideRetrofit");
        //设定30秒超时
        //构建Retrofit
        return new Retrofit.Builder()
                //配置服务器路径
                .baseUrl(mBaseUrl + "/")
                //设置日期解析格式，这样可以直接解析Date类型
//                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                //配置转化库，默认是Gson
                .addConverterFactory(GsonConverterFactory.create())
                //配置回调库，采用RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //设置OKHttpClient为网络客户端
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    JobService provideJobService(Retrofit retrofit) {
        Log.i(TAG, "provideJobService");
        return retrofit.create(JobService.class);
    }
}
package com.wfc.test7.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.ActivityEvent;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by wangfengchen on 2017/1/5.
 */

public abstract class BaseFragment extends Fragment {

    protected final PublishSubject<ActivityEvent> lifecycleSubject = PublishSubject.create();

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleSubject.onNext(ActivityEvent.STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
    }

    @NonNull
    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final ActivityEvent event) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceObservable) {
                Observable<ActivityEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<ActivityEvent, Boolean>() {
                            @Override
                            public Boolean call(ActivityEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return sourceObservable.takeUntil(compareLifecycleObservable);
            }
        };
    }
}

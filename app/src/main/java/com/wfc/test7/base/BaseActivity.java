package com.wfc.test7.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.ActivityEvent;
import com.wfc.test7.MyApp;

import java.util.LinkedHashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;


public abstract class BaseActivity extends AppCompatActivity {

    protected final PublishSubject<ActivityEvent> lifecycleSubject = PublishSubject.create();

    protected Map<String, String> params = new LinkedHashMap<>();
    
    protected abstract void setupApp(MyApp app);

    protected abstract int layoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
//        ButterKnife.bind(this);
        setupApp((MyApp) getApplication());
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
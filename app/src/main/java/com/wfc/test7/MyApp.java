package com.wfc.test7;

import android.app.Application;

import com.wfc.test7.components.DaggerNetComponent;
import com.wfc.test7.components.NetComponent;
import com.wfc.test7.modules.AppModule;
import com.wfc.test7.modules.NetModule;

public class MyApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("http://apitest.shangshaban.com"))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();
    }

    public NetComponent getNetComponent() {
       return mNetComponent;
    }
}
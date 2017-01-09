package com.wfc.test7.components;

import com.wfc.test7.MainActivity;
import com.wfc.test7.apis.JobService;
import com.wfc.test7.modules.AppModule;
import com.wfc.test7.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
   void inject(MainActivity activity);

   JobService getJobService();
   // void inject(MyFragment fragment);
   // void inject(MyService service);
}
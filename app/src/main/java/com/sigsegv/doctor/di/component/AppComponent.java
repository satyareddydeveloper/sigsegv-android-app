package com.sigsegv.doctor.di.component;

import android.app.Application;

import com.sigsegv.doctor.base.BaseApplication;
import com.sigsegv.doctor.di.module.ActivityBuilderModule;
import com.sigsegv.doctor.di.module.AppModule;
import com.sigsegv.doctor.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ContextModule.class, AppModule.class, AndroidSupportInjectionModule.class, ActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}

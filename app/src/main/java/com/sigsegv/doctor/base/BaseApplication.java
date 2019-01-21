package com.sigsegv.doctor.base;

import com.sigsegv.doctor.di.component.AppComponent;
import com.sigsegv.doctor.di.component.DaggerAppComponent;

import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

@Singleton
public class BaseApplication extends DaggerApplication {

    /**
     * Inject Dagger 2 component to the application
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }
}

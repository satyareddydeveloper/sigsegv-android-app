package com.sigsegv.doctor.base;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.sigsegv.doctor.di.component.AppComponent;
import com.sigsegv.doctor.di.component.DaggerAppComponent;

import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

@Singleton
public class BaseApplication extends DaggerApplication {

    public static final String CHANNEL_ID = "sigsegv";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Material Lock",
                    NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent component = DaggerAppComponent.builder()
                .application(this).build();
        component.inject(this);
        return component;
    }
}

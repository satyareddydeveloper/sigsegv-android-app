package com.sigsegv.doctor.di.module;

import com.sigsegv.doctor.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {MainActivityBuilderModule.class})
    abstract MainActivity bindMainActivity();
}

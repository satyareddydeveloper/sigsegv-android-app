package com.sigsegv.doctor.di.module;

import com.sigsegv.doctor.ui.auth.AuthActivity;
import com.sigsegv.doctor.ui.main.MainActivity;
import com.sigsegv.doctor.ui.start.StartActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract StartActivity bindStartActivity();

    @ContributesAndroidInjector
    abstract AuthActivity bindAuthActivity();

    @ContributesAndroidInjector(modules = {MainActivityBuilderModule.class})
    abstract MainActivity bindMainActivity();
}

package com.sigsegv.doctor.di.module;

import com.sigsegv.doctor.ui.auth.AuthFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector
    abstract AuthFragment provideAuthFragmentFactory();
}

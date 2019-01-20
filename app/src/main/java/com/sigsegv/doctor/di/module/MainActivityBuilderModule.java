package com.sigsegv.doctor.di.module;

import com.sigsegv.doctor.ui.doctors.DoctorsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector
    abstract DoctorsFragment provideDoctorsFragmentFactory();
}

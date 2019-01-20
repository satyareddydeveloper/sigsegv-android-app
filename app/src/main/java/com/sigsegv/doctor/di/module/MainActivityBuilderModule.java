package com.sigsegv.doctor.di.module;

import com.sigsegv.doctor.ui.doctor.DoctorFragment;
import com.sigsegv.doctor.ui.doctors.DoctorsFragment;
import com.sigsegv.doctor.ui.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector
    abstract DoctorsFragment provideDoctorsFragmentFactory();

    @ContributesAndroidInjector
    abstract DoctorFragment provideDoctorFragmentFactory();

    @ContributesAndroidInjector
    abstract SearchFragment provideSearchFragmentFactory();
}

package com.sigsegv.doctor.di.module;

import com.sigsegv.doctor.ui.comments.CommentsFragment;
import com.sigsegv.doctor.ui.doctor.DoctorFragment;
import com.sigsegv.doctor.ui.doctors.DoctorsFragment;
import com.sigsegv.doctor.ui.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Provide fragment injectors of MainActivity
 */
@Module
public abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector
    abstract DoctorsFragment provideDoctorsFragmentFactory();

    @ContributesAndroidInjector
    abstract DoctorFragment provideDoctorFragmentFactory();

    @ContributesAndroidInjector
    abstract SearchFragment provideSearchFragmentFactory();

    @ContributesAndroidInjector
    abstract CommentsFragment provideCommentsFragmentFactory();
}

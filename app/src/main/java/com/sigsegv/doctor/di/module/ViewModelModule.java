package com.sigsegv.doctor.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.sigsegv.doctor.di.util.ViewModelFactory;
import com.sigsegv.doctor.di.util.ViewModelKey;
import com.sigsegv.doctor.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
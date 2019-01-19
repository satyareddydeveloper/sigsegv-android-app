package com.sigsegv.doctor.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<B extends ViewDataBinding, A extends AppCompatActivity> extends DaggerFragment {

    private B binding;
    private A activity;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (A) context;
    }

    protected B getBinding() {
        return binding;
    }

    public A getBaseActivity() {
        return activity;
    }
}

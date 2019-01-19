package com.sigsegv.doctor.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<B extends ViewDataBinding> extends DaggerAppCompatActivity {

    private B binding;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutRes());
        binding.setLifecycleOwner(this);
        setTitle("");
    }

    protected B getBinding() {
        return binding;
    }
}

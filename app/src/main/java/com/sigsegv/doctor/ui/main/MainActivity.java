package com.sigsegv.doctor.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseActivity;
import com.sigsegv.doctor.databinding.ActivityMainBinding;

@SuppressLint("CommitTransaction")
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, DoctorsFragment.newInstance()).commit();
    }
}

package com.sigsegv.doctor.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseActivity;
import com.sigsegv.doctor.databinding.ActivityMainBinding;
import com.sigsegv.doctor.ui.doctors.DoctorsFragment;
import com.sigsegv.doctor.ui.search.SearchFragment;

import javax.inject.Inject;

@SuppressLint("CommitTransaction")
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Handle bottom navigation item selects
        getBinding().bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_doctors:
                    openFragment(DoctorsFragment.newInstance());
                    break;
                case R.id.action_search:
                    openFragment(SearchFragment.newInstance());
                    break;
            }
            return false;
        });

        //Open initial fragment
        openFragment(DoctorsFragment.newInstance());
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }
}

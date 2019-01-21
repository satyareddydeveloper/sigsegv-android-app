package com.sigsegv.doctor.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseActivity;
import com.sigsegv.doctor.databinding.ActivityMainBinding;
import com.sigsegv.doctor.rest.model.Province;
import com.sigsegv.doctor.ui.auth.AuthActivity;
import com.sigsegv.doctor.ui.doctors.DoctorsFragment;
import com.sigsegv.doctor.ui.search.SearchFragment;
import com.sigsegv.doctor.util.PrefUtils;

import java.util.ArrayList;

import javax.inject.Inject;

@SuppressLint("CommitTransaction")
public class MainActivity extends BaseActivity<ActivityMainBinding> implements ProvinceSelectDialog.DialogCallback {

    //Inject Dagger 2 provided classes
    @Inject PrefUtils prefUtils;
    @Inject ViewModelProvider.Factory viewModelFactory;

    private MainViewModel viewModel;
    private final ArrayList<Province> provinces = new ArrayList<>();

    @Override //Send layout id to super class
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        //Set custom actionBar
        setSupportActionBar(getBinding().toolbar);

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

        //Storage provinces on the view
        viewModel.getProvinces().observe(this, response -> {
            if (response != null) {
                provinces.clear();
                provinces.addAll(response);
            }
        });

        viewModel.getCurrentProvince().observe(this, province -> {
            if (province != null) {
                getBinding().tvProvince.setText(province.getTitle());
            }
        });

        //Open initial fragment
        openFragment(DoctorsFragment.newInstance());
    }

    @Override
    public void onProvinceSelected(Province province) {
        viewModel.setCurrentProvince(province);
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                prefUtils.setUserToken("");
                startActivity(new Intent(this, AuthActivity.class));
                finish();
                break;
            case R.id.action_location:
                ProvinceSelectDialog provinceSelectDialog = new ProvinceSelectDialog();
                provinceSelectDialog.setDialogCallback(this);
                provinceSelectDialog.submitData(provinces);
                provinceSelectDialog.show(getSupportFragmentManager(), "ProvinceSelector");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.sigsegv.doctor.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseActivity;
import com.sigsegv.doctor.databinding.ActivityMainBinding;
import com.sigsegv.doctor.ui.auth.AuthActivity;
import com.sigsegv.doctor.ui.doctors.DoctorsFragment;
import com.sigsegv.doctor.ui.search.SearchFragment;
import com.sigsegv.doctor.util.PrefUtils;

import javax.inject.Inject;

@SuppressLint("CommitTransaction")
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Inject PrefUtils prefUtils;

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //Open initial fragment
        openFragment(DoctorsFragment.newInstance());
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
        if (item.getItemId() == R.id.action_logout) {
            prefUtils.setUserToken("");
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

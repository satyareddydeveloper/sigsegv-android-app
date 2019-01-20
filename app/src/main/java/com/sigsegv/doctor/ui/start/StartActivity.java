package com.sigsegv.doctor.ui.start;

import android.content.Intent;
import android.os.Bundle;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.ui.auth.AuthActivity;
import com.sigsegv.doctor.ui.main.MainActivity;
import com.sigsegv.doctor.util.PrefUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class StartActivity extends DaggerAppCompatActivity {

    @Inject PrefUtils prefUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Redirect to the auth page if not authenticated
        if (!prefUtils.getUserToken().equals(""))
            startActivity(new Intent(this, MainActivity.class));
        else
            startActivity(new Intent(this, AuthActivity.class));

        finish();
    }
}

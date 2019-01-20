package com.sigsegv.doctor.ui.auth;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseActivity;
import com.sigsegv.doctor.databinding.ActivityAuthBinding;
import com.sigsegv.doctor.ui.main.MainActivity;

import javax.inject.Inject;

public class AuthActivity extends BaseActivity<ActivityAuthBinding> {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    private AuthViewModel viewModel;

    @Override //Send layout id to super class
    protected int layoutRes() {
        return R.layout.activity_auth;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthViewModel.class);

        //Handle button clicks
        getBinding().tvToggle.setOnClickListener(v -> {
            if (!getBinding().getProcessing()) getBinding().setPage(getBinding().getPage() == 0 ? 1 : 0);
        });

        getBinding().btProcess.setOnClickListener(v -> {

            //Get input values
            final String email = getBinding().etEmail.getText().toString().trim();
            final String name = getBinding().etName.getText().toString().trim();
            final String password = getBinding().etPassword.getText().toString().trim();

            switch (getBinding().getPage()) {
                case 0:

                    if (email.length() < 3 || password.length() < 3) {
                        Toast.makeText(getBaseContext(), "error!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Show processing indicator
                    getBinding().setProcessing(true);

                    viewModel.signIn(email, password);
                    break;
                case 1:

                    if (email.length() < 3 || password.length() < 3) {
                        Toast.makeText(getBaseContext(), "error!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Show processing indicator
                    getBinding().setProcessing(true);

                    viewModel.signUp(email, name, password);
                    break;

                case 2:

                    break;
            }
        });

        //Start observing liveData of the signIn response
        viewModel.getSignInResult().observe(this, authResponse -> {
            if (authResponse != null) {

                //Hide processing indicator
                getBinding().setProcessing(false);

                if (authResponse.getStatus()) {
                    Toast.makeText(getBaseContext(), "Success!", Toast.LENGTH_SHORT).show();

                    //Save user token to shared preferences


                    //Login success, redirect to main
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else
                    Toast.makeText(getBaseContext(), authResponse.getError(), Toast.LENGTH_SHORT).show();
            }
        });

        //Start observing liveData of the signUp response
        viewModel.getSignUpResult().observe(this, authResponse -> {
            if (authResponse != null) {

                //Hide processing indicator
                getBinding().setProcessing(false);

                if (authResponse.getStatus()) {
                    Toast.makeText(getBaseContext(), "Success!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getBaseContext(), authResponse.getError(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

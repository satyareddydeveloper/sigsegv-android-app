package com.sigsegv.doctor.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.ui.auth.AuthFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, AuthFragment.newInstance()).commit();
    }
}

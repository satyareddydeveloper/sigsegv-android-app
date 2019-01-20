package com.sigsegv.doctor.ui.start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.ui.auth.AuthActivity;
import com.sigsegv.doctor.ui.main.MainActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

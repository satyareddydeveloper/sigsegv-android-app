package com.sigsegv.doctor.ui.auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentAuthBinding;
import com.sigsegv.doctor.ui.main.MainActivity;

public class AuthFragment extends BaseFragment<FragmentAuthBinding, MainActivity> {

    public static AuthFragment newInstance() {
        return new AuthFragment();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_auth;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBinding().tvToggle.setOnClickListener(v -> getBinding().setPage(getBinding().getPage() == 0 ? 1 : 0));
        getBinding().tvForgot.setOnClickListener(v -> getBinding().setPage(2));
    }
}

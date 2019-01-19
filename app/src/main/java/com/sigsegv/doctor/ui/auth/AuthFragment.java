package com.sigsegv.doctor.ui.auth;

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
}

package com.sigsegv.doctor.ui.doctors;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentDoctorsBinding;
import com.sigsegv.doctor.ui.main.MainActivity;

public class DoctorsFragment extends BaseFragment<FragmentDoctorsBinding, MainActivity> {

    @Override
    protected int layoutRes() {
        return R.layout.fragment_doctors;
    }

    public static DoctorsFragment newInstance() {
        return new DoctorsFragment();
    }
}

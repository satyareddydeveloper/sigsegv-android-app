package com.sigsegv.doctor.ui.doctor;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentDoctorBinding;
import com.sigsegv.doctor.ui.main.MainActivity;

import javax.inject.Inject;

public class DoctorFragment extends BaseFragment<FragmentDoctorBinding, MainActivity> {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    private DoctorViewModel viewModel;
    private final CommentAdapter adapter = new CommentAdapter();

    public static DoctorFragment newInstance() {
        return  new DoctorFragment();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DoctorViewModel.class);

        getBinding().rvComments.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        getBinding().rvComments.setAdapter(adapter);

        viewModel.getDoctor().observe(this, doctor -> getBinding().setDoctor(doctor));
    }
}

package com.sigsegv.doctor.ui.search;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentDoctorBinding;
import com.sigsegv.doctor.databinding.FragmentSearchBinding;
import com.sigsegv.doctor.ui.doctor.DoctorViewModel;
import com.sigsegv.doctor.ui.main.MainActivity;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, MainActivity> {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    private DoctorViewModel viewModel;

    public static SearchFragment newInstance() {
        return  new SearchFragment();
    }

    @Override //Send layout id to super class
    protected int layoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DoctorViewModel.class);

        //viewModel.getDoctor().observe(this, doctor -> getBinding().setDoctor(doctor));
    }
}

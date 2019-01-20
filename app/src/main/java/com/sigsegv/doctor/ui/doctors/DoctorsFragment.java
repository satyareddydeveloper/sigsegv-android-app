package com.sigsegv.doctor.ui.doctors;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentDoctorsBinding;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.ui.main.MainActivity;

import javax.inject.Inject;

public class DoctorsFragment extends BaseFragment<FragmentDoctorsBinding, MainActivity> implements
        DoctorsAdapter.AdapterCallback {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    private DoctorsViewModel viewModel;
    private final DoctorsAdapter doctorsAdapter = new DoctorsAdapter(this);

    public static DoctorsFragment newInstance() {
        return new DoctorsFragment();
    }

    @Override //Send layout id to super class
    protected int layoutRes() {
        return R.layout.fragment_doctors;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DoctorsViewModel.class);

        //Initialize recyclerView
        getBinding().rvDoctors.setLayoutManager(new LinearLayoutManager(getBaseActivity()));
        getBinding().rvDoctors.setAdapter(doctorsAdapter);

        //Handle data refresh
        getBinding().swipeRefreshLayout.setOnRefreshListener(() -> viewModel.fetchDoctors());

        //Send fetched data to recyclerView adapter
        viewModel.getDoctors().observe(this, doctors -> {
            if (doctors != null) {
                getBinding().swipeRefreshLayout.setRefreshing(false);
                doctorsAdapter.submitList(doctors);
            }
        });
    }

    @Override
    public void onDoctorSelected(Doctor doctor) {
        getBaseActivity().showDoctor(doctor);
    }
}

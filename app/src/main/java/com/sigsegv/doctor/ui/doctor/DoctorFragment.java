package com.sigsegv.doctor.ui.doctor;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.sigsegv.doctor.R;
import com.sigsegv.doctor.base.BaseFragment;
import com.sigsegv.doctor.databinding.FragmentDoctorBinding;
import com.sigsegv.doctor.ui.comments.CommentsFragment;
import com.sigsegv.doctor.ui.main.MainActivity;

import javax.inject.Inject;

public class DoctorFragment extends BaseFragment<FragmentDoctorBinding, MainActivity> {

    //Inject Dagger 2 provided classes
    @Inject ViewModelProvider.Factory viewModelFactory;

    public static DoctorFragment newInstance() {
        return  new DoctorFragment();
    }

    @Override //Send layout id to super class
    protected int layoutRes() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final DoctorViewModel viewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DoctorViewModel.class);

        //Open comments fragment
        getBinding().btComments.setOnClickListener(v -> getBaseActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, CommentsFragment.newInstance()).addToBackStack(null).commit());

        //Setup ratingBar
        getBinding().rate.setMax(5);

        //Start observing liveData
        viewModel.getDoctor().observe(this, doctor -> {
            getBinding().setDoctor(doctor);
            if (doctor != null)
                getBinding().rate.setRating(doctor.getRating());
        });
    }
}

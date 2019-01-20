package com.sigsegv.doctor.ui.doctor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.model.Comment;
import com.sigsegv.doctor.rest.model.Doctor;

import javax.inject.Inject;

public class DoctorViewModel extends ViewModel {

    private final MutableLiveData<Doctor> doctor = new MutableLiveData<>();
    private final MutableLiveData<PagedList<Comment>> comments = new MutableLiveData<>();

    @Inject
    DoctorViewModel(RestRepository restRepository) {

    }

    public void setDoctor(Doctor doctor) {
        this.doctor.setValue(doctor);
    }

    LiveData<Doctor> getDoctor() {
        return doctor;
    }
}

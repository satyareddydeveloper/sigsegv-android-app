package com.sigsegv.doctor.ui.doctors;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.datasource.DoctorDataSource;
import com.sigsegv.doctor.rest.model.Doctor;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DoctorsViewModel extends ViewModel {

    private final RestRepository restRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private final MutableLiveData<PagedList<Doctor>> doctors = new MutableLiveData<>();

    private final DoctorDataSource doctorDataSource;

    @Inject
    public DoctorsViewModel(RestRepository restRepository) {
        this.restRepository = restRepository;
        doctorDataSource = new DoctorDataSource(disposable, restRepository);
        fetchDoctors();
    }

    void fetchDoctors() {
        doctors.setValue(new PagedList.Builder<>(doctorDataSource, new PagedList.Config.Builder().setPageSize(20)
                .setInitialLoadSizeHint(10).setEnablePlaceholders(true).build()).build());
    }
}

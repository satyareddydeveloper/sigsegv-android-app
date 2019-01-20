package com.sigsegv.doctor.ui.doctors;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.datasource.DoctorDataSource;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.util.MainThreadExecutor;
import com.sigsegv.doctor.util.PrefUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DoctorsViewModel extends ViewModel {

    private final MainThreadExecutor executor = new MainThreadExecutor();
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final DoctorDataSource doctorDataSource;

    private final MutableLiveData<PagedList<Doctor>> doctors = new MutableLiveData<>();

    @Inject
    DoctorsViewModel(PrefUtils prefUtils, RestRepository restRepository) {
        doctorDataSource = new DoctorDataSource(prefUtils.getUserToken(), disposable, restRepository);
        fetchDoctors();
    }

    void fetchDoctors() {
        doctors.setValue(new PagedList.Builder<>(doctorDataSource, new PagedList.Config.Builder().setPageSize(20)
                .setInitialLoadSizeHint(20).setEnablePlaceholders(true).build())
                .setFetchExecutor(executor).setNotifyExecutor(executor).build());
    }

    LiveData<PagedList<Doctor>> getDoctors() {
        return doctors;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}

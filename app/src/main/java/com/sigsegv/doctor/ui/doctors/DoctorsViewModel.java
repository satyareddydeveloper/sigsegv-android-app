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

    //Initialize required objects
    private final PrefUtils prefUtils;
    private final RestRepository restRepository;
    private final MainThreadExecutor executor = new MainThreadExecutor();
    private final CompositeDisposable disposable = new CompositeDisposable();

    //Mutable data to send background process results to view
    private final MutableLiveData<PagedList<Doctor>> doctors = new MutableLiveData<>();
    private String keyword = "";

    @Inject
    DoctorsViewModel(PrefUtils prefUtils, RestRepository restRepository) {
        this.prefUtils = prefUtils;
        this.restRepository = restRepository;
        fetchDoctors();
    }

    void fetchDoctors() {
        final DoctorDataSource doctorDataSource = new DoctorDataSource(prefUtils.getUserToken(), keyword, disposable, restRepository);
        doctors.setValue(new PagedList.Builder<>(doctorDataSource, new PagedList.Config.Builder().setPageSize(20)
                .setInitialLoadSizeHint(20).setEnablePlaceholders(true).build())
                .setFetchExecutor(executor).setNotifyExecutor(executor).build());
    }

    void setKeyword(String keyword) {
        //this.keyword = keyword;
        //fetchDoctors();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        //Dispose process queue on clear
        disposable.dispose();
    }

    //Getters for view
    LiveData<PagedList<Doctor>> getDoctors() {
        return doctors;
    }
}

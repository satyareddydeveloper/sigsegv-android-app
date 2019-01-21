package com.sigsegv.doctor.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.model.Province;
import com.sigsegv.doctor.util.PrefUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    //Initialize required objects
    private final CompositeDisposable disposable = new CompositeDisposable();

    //Mutable data to send background process results to view
    private final MutableLiveData<ArrayList<Province>> provinces = new MutableLiveData<>();
    private final MutableLiveData<Province> currentProvince = new MutableLiveData<>();

    @Inject
    MainViewModel(PrefUtils prefUtils, RestRepository restRepository) {
        disposable.add(restRepository.getProvinces(prefUtils.getUserToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ArrayList<Province>>() {
                    @Override
                    public void onSuccess(ArrayList<Province> response) {
                        provinces.postValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    void setCurrentProvince(Province province) {
        currentProvince.setValue(province);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        //Dispose process queue on clear
        disposable.dispose();
    }

    //Getters for view
    LiveData<ArrayList<Province>> getProvinces() {
        return provinces;
    }
    LiveData<Province> getCurrentProvince() {
        return currentProvince;
    }
}

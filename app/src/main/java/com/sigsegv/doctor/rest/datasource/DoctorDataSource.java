package com.sigsegv.doctor.rest.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.rest.model.PagedResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DoctorDataSource extends PageKeyedDataSource<Integer, Doctor> {

    private static final String TAG = "DoctorDataSource";

    private final String token;
    private final CompositeDisposable disposable;
    private final RestRepository restRepository;

    public DoctorDataSource(String token, CompositeDisposable disposable, RestRepository restRepository) {
        this.token = token;
        this.disposable = disposable;
        this.restRepository = restRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Doctor> callback) {
        disposable.add(restRepository.getDoctors(token, 1, "").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PagedResponse<Doctor>>() {
                    @Override
                    public void onSuccess(PagedResponse<Doctor> response) {
                        Log.d(TAG, "Retrofit success!");

                        //Send callback to data source observer
                        callback.onResult(response.getResults(), 0,
                                response.getCount(), null, 2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Retrofit Error!", e);
                    }
                }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Doctor> callback) {
        disposable.add(restRepository.getDoctors(token, params.key, "").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PagedResponse<Doctor>>() {
                    @Override
                    public void onSuccess(PagedResponse<Doctor> response) {
                        Log.d(TAG, "Retrofit success!");

                        //Send callback to data source observer
                        callback.onResult(response.getResults(), params.key + 1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Retrofit Error!", e);
                    }
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Doctor> callback) {

    }
}

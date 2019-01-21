package com.sigsegv.doctor.rest.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.rest.model.PagedResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DoctorDataSource extends PageKeyedDataSource<Integer, Doctor> {

    private final String token;
    private final String keyword;
    private final CompositeDisposable disposable;
    private final RestRepository restRepository;

    public DoctorDataSource(String token, String keyword, CompositeDisposable disposable, RestRepository restRepository) {
        this.token = token;
        this.keyword = keyword;
        this.disposable = disposable;
        this.restRepository = restRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Doctor> callback) {
        disposable.add(restRepository.getDoctors(token, 1, keyword).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PagedResponse<Doctor>>() {
                    @Override
                    public void onSuccess(PagedResponse<Doctor> response) {

                        //Send callback to data source observer
                        callback.onResult(response.getResults(), 0,
                                response.getCount(), null, 2);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Doctor> callback) {
        disposable.add(restRepository.getDoctors(token, params.key, keyword).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PagedResponse<Doctor>>() {
                    @Override
                    public void onSuccess(PagedResponse<Doctor> response) {

                        //Send callback to data source observer
                        callback.onResult(response.getResults(), params.key + 1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Doctor> callback) {

    }
}

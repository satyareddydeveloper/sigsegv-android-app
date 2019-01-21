package com.sigsegv.doctor.ui.doctor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.datasource.CommentDataSource;
import com.sigsegv.doctor.rest.model.Comment;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.util.MainThreadExecutor;
import com.sigsegv.doctor.util.PrefUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DoctorViewModel extends ViewModel {

    //Initialize required objects
    private final PrefUtils prefUtils;
    private final RestRepository restRepository;
    private final MainThreadExecutor executor = new MainThreadExecutor();
    private final CompositeDisposable disposable = new CompositeDisposable();

    //Mutable data to send background process results to view
    private final MutableLiveData<Doctor> doctor = new MutableLiveData<>();
    private final MutableLiveData<PagedList<Comment>> comments = new MutableLiveData<>();

    @Inject
    DoctorViewModel(PrefUtils prefUtils, RestRepository restRepository) {
        this.prefUtils = prefUtils;
        this.restRepository = restRepository;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor.setValue(doctor);
        fetchComments();
    }

    public void fetchComments() {
        if (doctor.getValue() != null) {
            final CommentDataSource commentDataSource = new CommentDataSource(prefUtils.getUserToken(),
                    String.valueOf(doctor.getValue().getId()), disposable, restRepository);

            //Set dataSource data to liveData
            comments.setValue(new PagedList.Builder<>(commentDataSource, new PagedList.Config.Builder().setPageSize(20)
                    .setInitialLoadSizeHint(20).setEnablePlaceholders(true).build())
                    .setFetchExecutor(executor).setNotifyExecutor(executor).build());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        //Dispose process queue on clear
        disposable.dispose();
    }

    //Getters for view
    LiveData<Doctor> getDoctor() {
        return doctor;
    }
    public LiveData<PagedList<Comment>> getComments() {
        return comments;
    }
}

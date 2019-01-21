package com.sigsegv.doctor.rest.datasource;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.model.Comment;
import com.sigsegv.doctor.rest.model.PagedResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CommentDataSource extends PageKeyedDataSource<Integer, Comment> {

    private final String token;
    private final String keyword;
    private final CompositeDisposable disposable;
    private final RestRepository restRepository;

    public CommentDataSource(String token, String keyword, CompositeDisposable disposable, RestRepository restRepository) {
        this.keyword = keyword;
        this.token = token;
        this.disposable = disposable;
        this.restRepository = restRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Comment> callback) {
        disposable.add(restRepository.getComments(token, keyword, 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PagedResponse<Comment>>() {
                    @Override
                    public void onSuccess(PagedResponse<Comment> response) {

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
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Comment> callback) {
        disposable.add(restRepository.getComments(token, keyword, params.key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<PagedResponse<Comment>>() {
                    @Override
                    public void onSuccess(PagedResponse<Comment> response) {

                        //Send callback to data source observer
                        callback.onResult(response.getResults(), params.key + 1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Comment> callback) {

    }
}

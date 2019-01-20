package com.sigsegv.doctor.ui.auth;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sigsegv.doctor.rest.RestRepository;
import com.sigsegv.doctor.rest.model.NormalResponse;
import com.sigsegv.doctor.rest.model.TokenReponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private final RestRepository restRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();

    //Mutable data to send background process results to view
    private final MutableLiveData<AuthResponse> signInResult = new MutableLiveData<>();
    private final MutableLiveData<AuthResponse> signUpResult = new MutableLiveData<>();

    @Inject
    AuthViewModel(RestRepository restRepository) {
        this.restRepository = restRepository;
    }

    //Check credentials on server
    void signIn(String email, String password) {
        disposable.add(restRepository.signIn(email, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<TokenReponse>() {
                    @Override
                    public void onSuccess(TokenReponse tokenReponse) {
                        signInResult.postValue(new AuthResponse(true, null, tokenReponse.getToken()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        signInResult.postValue(new AuthResponse(false, e.getMessage(), null));
                    }
                }));
    }

    //Send credentials to server and save if it's valid
    void signUp(String email, String name, String password) {
        disposable.add(restRepository.signUp(email, name, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<NormalResponse>() {
                    @Override
                    public void onSuccess(NormalResponse normalResponse) {
                        signUpResult.postValue(new AuthResponse(true, null, null));
                    }

                    @Override
                    public void onError(Throwable e) {
                        signUpResult.postValue(new AuthResponse(false, e.getMessage(), null));
                    }
                }));
    }

    //Getters for view
    LiveData<AuthResponse> getSignInResult() {
        return signInResult;
    }
    LiveData<AuthResponse> getSignUpResult() {
        return signUpResult;
    }
}

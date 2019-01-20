package com.sigsegv.doctor.rest;

import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.rest.model.NormalResponse;
import com.sigsegv.doctor.rest.model.PagedResponse;
import com.sigsegv.doctor.rest.model.TokenReponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class RestRepository {

    private RestService restService;

    @Inject
    public RestRepository(RestService restService) {
        this.restService = restService;
    }

    public Single<TokenReponse> signIn(String email, String password) {
        return restService.signIn(email, password);
    }

    public Single<NormalResponse> signUp(String email, String name, String password) {
        return restService.signUp(email, name, password);
    }

    public Single<PagedResponse<Doctor>> getDoctors(int page, String province) {
        return restService.getDoctors(page, province);
    }
}

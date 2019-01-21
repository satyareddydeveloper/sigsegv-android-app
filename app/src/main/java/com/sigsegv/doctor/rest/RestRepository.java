package com.sigsegv.doctor.rest;

import com.sigsegv.doctor.rest.model.Comment;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.rest.model.NormalResponse;
import com.sigsegv.doctor.rest.model.PagedResponse;
import com.sigsegv.doctor.rest.model.Province;
import com.sigsegv.doctor.rest.model.TokenResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;

public class RestRepository {

    private RestService restService;

    @Inject
    public RestRepository(RestService restService) {
        this.restService = restService;
    }

    public Single<TokenResponse> signIn(String email, String password) {
        return restService.signIn(email, password);
    }

    public Single<NormalResponse> signUp(String email, String name, String password) {
        return restService.signUp(email, name, password);
    }

    public Single<PagedResponse<Doctor>> getDoctors(String token, int page, String province) {
        return restService.getDoctors(String.format("Token %s", token), page, province);
    }

    public Single<PagedResponse<Comment>> getComments(String token, String keyword, int page) {
        return restService.getComments(String.format("Token %s", token), keyword, page);
    }

    public Single<ArrayList<Province>> getProvinces(String token) {
        return restService.getProvinces(String.format("Token %s", token));
    }
}

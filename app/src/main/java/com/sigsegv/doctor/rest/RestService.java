package com.sigsegv.doctor.rest;

import com.sigsegv.doctor.rest.model.NormalResponse;
import com.sigsegv.doctor.rest.model.TokenReponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestService {

    @FormUrlEncoded
    @POST("accounts/login/")
    Single<TokenReponse> signIn(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("accounts/profile/")
    Single<NormalResponse> signUp(@Field("username") String username, @Field("name") String name, @Field("password") String password);
}

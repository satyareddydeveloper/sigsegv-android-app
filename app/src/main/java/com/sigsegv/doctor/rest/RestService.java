package com.sigsegv.doctor.rest;

import com.sigsegv.doctor.rest.model.Comment;
import com.sigsegv.doctor.rest.model.Doctor;
import com.sigsegv.doctor.rest.model.NormalResponse;
import com.sigsegv.doctor.rest.model.PagedResponse;
import com.sigsegv.doctor.rest.model.Province;
import com.sigsegv.doctor.rest.model.TokenResponse;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestService {

    @FormUrlEncoded
    @POST("accounts/login/")
    Single<TokenResponse> signIn(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("accounts/profile/")
    Single<NormalResponse> signUp(@Field("email") String username, @Field("name") String name, @Field("password") String password);

    @GET("doctors/doctor")
    Single<PagedResponse<Doctor>> getDoctors(@Header("Authorization") String token, @Query("page") int page, @Query("province") String province);

    @GET("doctors/doctor-comment")
    Single<PagedResponse<Comment>> getComments(@Header("Authorization") String token, @Query("search") String keyword, @Query("page") int page);

    @GET("doctors/province")
    Single<ArrayList<Province>> getProvinces(@Header("Authorization") String token);
}

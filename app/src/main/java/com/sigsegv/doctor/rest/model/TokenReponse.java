package com.sigsegv.doctor.rest.model;

import com.google.gson.annotations.SerializedName;

public class TokenReponse {

    @SerializedName("token")
    public String token;

    public TokenReponse(String token) {

    }

    public String getToken() {
        return token;
    }
}

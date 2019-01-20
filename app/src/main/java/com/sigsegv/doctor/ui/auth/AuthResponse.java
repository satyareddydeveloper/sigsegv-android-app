package com.sigsegv.doctor.ui.auth;

public class AuthResponse {

    private boolean status;
    private String error;
    private String token;

    public AuthResponse(boolean status, String error, String token) {
        this.status = status;
        this.error = error;
        this.token = token;
    }

    public boolean getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getToken() {
        return token;
    }
}

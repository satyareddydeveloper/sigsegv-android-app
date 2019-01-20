package com.sigsegv.doctor.ui.auth;

class AuthResponse {

    private boolean status;
    private String error;
    private String token;

    AuthResponse(boolean status, String error, String token) {
        this.status = status;
        this.error = error;
        this.token = token;
    }

    boolean getStatus() {
        return status;
    }

    String getError() {
        return error;
    }

    String getToken() {
        return token;
    }
}

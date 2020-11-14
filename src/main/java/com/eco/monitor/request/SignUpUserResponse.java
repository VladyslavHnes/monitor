package com.eco.monitor.request;

public class SignUpUserResponse {

    private String token;

    public SignUpUserResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

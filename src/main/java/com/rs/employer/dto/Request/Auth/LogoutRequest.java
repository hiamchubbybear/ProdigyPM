package com.rs.employer.dto.Request.Auth;

public class LogoutRequest {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LogoutRequest() {
    }

    public LogoutRequest(String token) {
        this.token = token;
    }

}

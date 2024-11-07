package com.rs.employer.dto.Respone;

public class LoginInactiveAccountRespone {
    String token;
    boolean authenticated;

    public LoginInactiveAccountRespone() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public LoginInactiveAccountRespone(String token, boolean authenticated) {
        this.token = token;
        this.authenticated = authenticated;
    }
}

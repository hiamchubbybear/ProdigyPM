package com.rs.employer.dto.Request;

public class ForgotAccountRequest {
     String username;
     String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgotAccountRequest(String username, String newPassword, String email) {
        this.username = username;
        this.email = email;
    }

    public ForgotAccountRequest() {
    }
}

package com.rs.employer.dto.Request;

public class ForgotAccountRequest {
     String username;
     String newPassword;
     String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgotAccountRequest(String username, String newPassword, String email) {
        this.username = username;
        this.newPassword = newPassword;
        this.email = email;
    }

    public ForgotAccountRequest() {
    }
}

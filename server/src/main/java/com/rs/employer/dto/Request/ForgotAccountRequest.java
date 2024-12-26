package com.rs.employer.dto.Request;

public class ForgotAccountRequest {
     String email;

    public ForgotAccountRequest() {
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ForgotAccountRequest(String email) {
        this.email = email;
    }
}

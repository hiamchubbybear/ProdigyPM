package com.rs.employer.dto.Request;

public class ActivateRequestAccount {
    String username;
    String email;

    public ActivateRequestAccount(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public ActivateRequestAccount() {
    }

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
}

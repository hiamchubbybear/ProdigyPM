package com.rs.employer.dto.Response;

public class RegisterRespone {
    private String username;
    private String password;
    private String email;

    public RegisterRespone(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public RegisterRespone() {
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

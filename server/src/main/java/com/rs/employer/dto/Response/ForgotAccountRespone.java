package com.rs.employer.dto.Response;

public class ForgotAccountRespone {
    public ForgotAccountRespone(boolean isSuccess, String message, String token) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.token = token;
    }

    boolean isSuccess;
    String message;
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

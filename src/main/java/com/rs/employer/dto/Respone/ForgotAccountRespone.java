package com.rs.employer.dto.Respone;

public class ForgotAccountRespone {
    public ForgotAccountRespone(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    boolean isSuccess;
    String message;

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

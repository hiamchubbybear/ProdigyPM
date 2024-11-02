package com.rs.employer.dto.Respone;
public class ResetPasswordRespone {
    boolean isSuccess;
    String message;

    public ResetPasswordRespone(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
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


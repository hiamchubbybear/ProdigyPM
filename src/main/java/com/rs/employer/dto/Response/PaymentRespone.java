package com.rs.employer.dto.Response;

public class PaymentRespone {
    boolean isSuccess;
    String message;
    String url;
    public PaymentRespone() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PaymentRespone(boolean isSuccess, String message, String url) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.url = url;
    }

    public PaymentRespone(boolean isSuccess, String message, Double cash) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}

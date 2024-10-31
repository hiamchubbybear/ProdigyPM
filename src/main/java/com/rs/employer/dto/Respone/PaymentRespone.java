package com.rs.employer.dto.Respone;

public class PaymentRespone {
    boolean isSuccess;
    String message;

    public PaymentRespone() {
    }

    public PaymentRespone(boolean isSuccess, String message, Double cash) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.cash = cash;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
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

    Double cash;
}

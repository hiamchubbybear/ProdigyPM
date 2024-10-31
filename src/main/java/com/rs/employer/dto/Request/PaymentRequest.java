package com.rs.employer.dto.Request;

import java.time.Instant;

public class PaymentRequest {
    String id;
    String transaction;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public PaymentRequest(String id, String transaction) {
        this.id = id;
        this.transaction = transaction;
    }

    public PaymentRequest() {
    }

}

package com.rs.employer.model.others;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Invalid {
    @Id
    private String id;
    Date expireTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Date get() {
        return expireTime;
    }

    public void set(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Invalid(String id, Date expireTime) {
        this.id = id;
        this.expireTime = expireTime;
    }

    public Invalid() {
    }

}

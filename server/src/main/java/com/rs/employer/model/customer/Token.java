package com.rs.employer.model.customer;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;
    @Column(nullable = false)
    private boolean used;
    @Column(nullable = false )
    private LocalDateTime expireDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id" , nullable = false , updatable = true)
    private Customer customer;


    public Token() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isUsed() {
        return used;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Token(String id, String token, boolean used, LocalDateTime expireDate, Customer customer) {
        this.id = id;
        this.token = token;
        this.used = used;
        this.expireDate = expireDate;
        this.customer = customer;
    }
}

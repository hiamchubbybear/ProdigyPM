package com.rs.employer.dto.Request.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerRequest {
    private String name;
    private String address;
    private String username;
    private String roles;
    private boolean gender;
    private String password;
    private String status;
    private Instant create;
    private Instant update;
    private Long cart_id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    public CustomerRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreate() {
        return create;
    }

    public void setCreate(Instant create) {
        this.create = create;
    }

    public Instant getUpdate() {
        return update;
    }

    public void setUpdate(Instant update) {
        this.update = update;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public CustomerRequest(String name, String address, String username, String roles, boolean gender, String password, String status, Instant create, Instant update, Long cart_id, LocalDate dob) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.roles = roles;
        this.gender = gender;
        this.password = password;
        this.status = status;
        this.create = create;
        this.update = update;
        this.cart_id = cart_id;
        this.dob = dob;
    }
}

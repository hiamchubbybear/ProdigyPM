package com.rs.employer.dto.Respone;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

public class CustomerUpdateRespone {
//    @Size(min = 2, message = "PASSWORD_INVALID")
    @Nullable
    String password;
    String email;
    private String name;
    private String address;
    private String role;
    private boolean gender;
    private String status;
    private Instant update;
    private LocalDate dob;

    public CustomerUpdateRespone() {
    }

    public CustomerUpdateRespone(String password, String email, String name, String address, String role, boolean gender, String status, Instant update, LocalDate dob) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        this.role = role;
        this.gender = gender;
        this.status = status;
        this.update = update;
        this.dob = dob;
    }

    public @Size(min = 2, message = "PASSWORD_INVALID") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 2, message = "PASSWORD_INVALID") String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getUpdate() {
        return update;
    }

    public void setUpdate(Instant update) {
        this.update = update;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

}

package com.rs.employer.dto.Response;

import jakarta.transaction.Transactional;

import java.io.Serializable;

@Transactional
public class CustomerInfoDTO  implements Serializable {
    String username;
    String email;
    String name;
    String address;
    boolean gender;
    boolean status;
    String role;

    public CustomerInfoDTO(String username, String email, String name, String address, boolean gender, boolean status, String role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.role = role;
    }

    public CustomerInfoDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CustomerInfoDTO(String username, String email, String name, String address, boolean gender, boolean status) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.status = status;
    }
}

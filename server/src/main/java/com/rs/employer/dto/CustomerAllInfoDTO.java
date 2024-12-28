package com.rs.employer.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class CustomerAllInfoDTO implements Serializable {
    UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public CustomerAllInfoDTO(UUID uuid, String username, String email, String name, String address, boolean gender, boolean status, Instant createAt, LocalDate dob, String role) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.createAt = createAt;
        this.dob = dob;
        this.role = role;
    }

    String username;
    String email;
    String name;
    String address;
    boolean gender;
    boolean status;
    Instant createAt;
    LocalDate dob;
    String role;

    public CustomerAllInfoDTO() {
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

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CustomerAllInfoDTO(String username, String email, String name, String address, boolean gender, boolean status, Instant createAt, LocalDate dob, String role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.createAt = createAt;
        this.dob = dob;
        this.role = role;
    }
}

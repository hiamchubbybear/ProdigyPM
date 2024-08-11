package com.rs.employer.dto.Respone;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerRespone {
    private String name;
    private String address;
    private String username;
    private Set<RoleRespone> roles;
    private boolean gender;
    private String password;
    private String status;
    private Instant create;
    private Instant update;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    public Instant getCreate() {
        return this.create;
    }

    public void setCreate(Instant create) {
        this.create = create;
    }

    public Instant getUpdate() {
        return this.update;
    }

    public void setUpdate(Instant update) {
        this.update = update;
    };

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

    public Set<RoleRespone> getRole() {
        return roles;
    }

    public void setRole(Set<RoleRespone> role) {
        this.roles = role;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public CustomerRespone() {
    }

    public CustomerRespone(String name, String address, String username, Set<RoleRespone> role, boolean gender,
            String password, String status, LocalDate dob, Instant create, Instant update) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.roles = role;
        this.gender = gender;
        this.password = password;
        this.status = status;
        this.dob = dob;
        this.create = create;
        this.update = update;
    }

}

package com.rs.employer.dto.Request;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

public class CustomerRequest {
    private String name;
    private String address;
    private String username;
    private Set<String> roles;
    private boolean gender;
    private String password;
    private String status;
    private Date birthDay;
    private Instant create;
    private Instant update;

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
    }

    public CustomerRequest(String name, String address, String username, Set<RoleRequest> role, boolean gender,
            String password, String status, Date birthDay, Instant create, Instant update) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.roles = roles;
        this.gender = gender;
        this.password = password;
        this.status = status;
        this.birthDay = birthDay;
        this.create = create;
        this.update = update;
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

    public Set<String> getRole() {
        return roles;
    }

    public void setRole(Set<String> role) {
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String address, String username, Set<String> role, boolean gender,
            String password, String status, Date birthDay) {

        this.name = name;
        this.address = address;
        this.username = username;
        this.roles = role;
        this.gender = gender;
        this.password = password;
        this.status = status;
        this.birthDay = birthDay;
    }

}

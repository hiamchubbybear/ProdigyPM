package com.rs.employer.dto.Respone;

import java.util.Date;
import java.util.Set;

public class CustomerRespone {

    private String name;
    private String address;
    private String username;
    private Set<String> role;
    private boolean gender;
    private String password;
    private String status;
    private Date birthDay;

    public CustomerRespone(String name, String address, String username, Set<String> role, boolean gender, String password, String status, Date birthDay) {

        this.name = name;
        this.address = address;
        this.username = username;
        this.role = role;
        this.gender = gender;
        this.password = password;
        this.status = status;
        this.birthDay = birthDay;
    }

    // Getters and Setters
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
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
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

}
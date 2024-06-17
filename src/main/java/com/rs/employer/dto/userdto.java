package com.rs.employer.dto;

import java.sql.Time;



public class userdto {
    private String name;
    private String address;
    private String username;
    private String role;
    private boolean gender;
    private String password;
    private String status;
    private Time birthDay;

    public userdto(String name, String address, String username, String role, boolean gender, String status,
            Time birthDay) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.role = role;
        this.gender = gender;
        this.status = status;
        this.birthDay = birthDay;
    }

    public userdto(String name, String address, String username, String role, boolean gender, String password,
            String status, Time birthDay) {
        this.name = name;
        this.address = address;
        this.username = username;
        this.role = role;
        this.gender = gender;
        this.password = null;
        this.status = status;
        this.birthDay = birthDay;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    public Time getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Time birthDay) {
        this.birthDay = birthDay;
    }

}

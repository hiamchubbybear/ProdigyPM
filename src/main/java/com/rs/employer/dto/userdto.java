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

}

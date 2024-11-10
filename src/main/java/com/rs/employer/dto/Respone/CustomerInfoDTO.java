package com.rs.employer.dto.Respone;

public class CustomerInfoDTO {
    String email;
    String name;
    String address;
    boolean gender;
    boolean status;

    public CustomerInfoDTO() {
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

    public CustomerInfoDTO( String email, String name, String address, boolean gender, boolean status) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.status = status;
    }
}

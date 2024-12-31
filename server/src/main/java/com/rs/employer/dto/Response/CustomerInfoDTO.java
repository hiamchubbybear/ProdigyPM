package com.rs.employer.dto.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.transaction.Transactional;

import java.io.Serializable;

public class CustomerInfoDTO  implements Serializable {
    String username;
    String email;
    String name;
    String address;
    Boolean gender;
    String role;

    @JsonCreator
    public CustomerInfoDTO(
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("name") String name,
            @JsonProperty("address") String address,
            @JsonProperty("role") String role,
            @JsonProperty("gender") Boolean gender
    ) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.role = role;
        this.gender = gender;
    }

    public CustomerInfoDTO(String username, String email, String name, String address, Boolean gender, String role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.role = role;
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

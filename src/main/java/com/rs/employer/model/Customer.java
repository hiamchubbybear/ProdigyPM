package com.rs.employer.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Customer class
@Entity
@Table(name = "schema_user")
public class Customer {

    // UUID of the customer auto generated
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();
    // ID for the customer
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    // Name of the customer
    @Column(name = "name" , nullable= false , updatable=true)
    private String name;
    // Address of the customer
    @Column(name = "address" , nullable=false, updatable=true)
    private String address;
    // Username of the customer
    @Column(name = "username", nullable = false , updatable=false)
    private String username;
    // Password of the customer
    @Column(name = "password", nullable = false , updatable = true)
    private String password;
    // Role of the customer
    @Column(name = "role",nullable = true, updatable = true)
    private String role;
    // Gender of the customer
    @Column(name = "gender",nullable = false , updatable = true)
    private boolean gender;
    // Status of the customer
    @Column(name = "status", updatable= true , nullable=false) 
    private String status;
    // Birthday of the customer
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthDay;

    // Getters and Setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

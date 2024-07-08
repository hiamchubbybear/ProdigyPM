package com.rs.employer.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Customer class
// @Data
// @Setter
// @Getter
// @AllArgsConstructor
// @NoAllArgsConstructor
@Entity
@Table(name = "schema_customer")
public class Customer {
    // ID for the customer

    @Column(name = "id")
    private Long id;
    // UUID of the customer auto generated
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid = UUID.randomUUID();
    // Username of the customer
    @Column(name = "username", nullable = false, updatable = false)
    private String username;
    // Password of the customer
    @Column(name = "password", nullable = false, updatable = true)
    private String password;
    // Name of the customer
    @Column(name = "name", nullable = false, updatable = true)
    private String name;
    // Address of the customer
    @Column(name = "address", nullable = true, updatable = true)
    private String address;
    // Role of the customer
    @Column(name = "role", nullable = true, updatable = true)
    private String role;
    // Gender of the customer
    @Column(name = "gender", nullable = false, updatable = true)
    private boolean gender;
    // Status of the customer
    @Column(name = "status", updatable = true, nullable = false)
    private String status;
    // Birthday of the customer
    @Column(name = "create_at", nullable = false, updatable = false)
    private Instant create;
    @Column(name = "update_at", nullable = false, updatable = false)
    private Instant update;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Customer() {
    }

    public Customer(Long id, UUID uuid, String username, String password, String name, String address, String role,
            boolean gender, String status, Date birthDay) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;
        this.gender = gender;
        this.status = status;
        this.birthDay = birthDay;
    }

    public Instant getCreate() {
        return create;
    }

    public void setCreate(Instant create) {
        this.create = create;
    }

    public Instant getUpdate() {
        return update;
    }

    public void setUpdate(Instant update) {
        this.update = update;
    }

}

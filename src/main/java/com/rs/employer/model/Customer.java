package com.rs.employer.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rs.employer.ValidateAnotation.ValidateRole;
import com.rs.employer.ValidateAnotation.ValidateStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Customer class
// @Data
// @Setter
// @Getter
// @AllArgsConstructor
// @NoAllArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "schema_customer")
public class Customer {
    @Column(name = "user_id")
    private Long id;
    @Id
    @Column(name = "uuid")
    @UuidGenerator
    private UUID uuid;
    @NotNull
    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
    @Column(name = "username", nullable = false, updatable = false)
    private String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotBlank
    @Column(name = "password", nullable = false, updatable = true)
    private String password;
    // Name of the customer
    @Column(name = "name", nullable = false, updatable = true)
    private String name;
    // Address of the customer
    @Column(name = "address", nullable = true, updatable = true)
    private String address;
    // Role of the customer
    @Column(name = "role_id", nullable = true, updatable = true)
    @ValidateRole
    private String role;
    // Gender of the customer
    @Column(name = "gender", nullable = false, updatable = true)
    private boolean gender;
    @ValidateStatus
    @Column(name = "status")
    private String status;
    // Birthday of the customer
    @Column(name = "create_at", nullable = false, updatable = false)
    private Instant create;
    @LastModifiedDate
    @Column(name = "update_at")
    private Instant update;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthDay;
    @ManyToMany()
    @JsonIgnore
    @JoinTable(name = "customer_product",
     joinColumns = @JoinColumn(name = "customer_id"), 
     inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

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

    public Customer(Long id, UUID uuid, @NotNull @Size(min = 3, max = 20, message = "USERNAME_INVALID") String username,
            @Size(min = 8, message = "PASSWORD_INVALID") @NotBlank String password, String name, String address,
            String role, boolean gender, String status, Instant create, Instant update, Date birthDay,
            List<Product> products) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;
        this.gender = gender;
        this.status = status;
        this.create = create;
        this.update = update;
        this.birthDay = birthDay;
        this.products = products;
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

    public void setUpdate(Instant instant) {
        this.update = instant;
    }

    // public List<Product> getProducts() {
    // return products;
    // }

    // public void setProducts(List<Product> products) {
    // this.products = products;
    // }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}

package com.rs.employer.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rs.employer.ValidateAnotation.ValidateRole;
import com.rs.employer.ValidateAnotation.ValidateStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "schema_customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
  @Column(name = "user_id")
  Long userid;
  @Id
  @Column(name = "uuid")
  @UuidGenerator
  UUID uuid;
  @NotNull
  @Size(min = 3, max = 20, message = "USERNAME_INVALID")
  @Column(name = "username", nullable = false, updatable = false)
  String username;
  @Size(min = 8, message = "PASSWORD_INVALID")
  @NotBlank
  @Column(name = "password", nullable = false, updatable = true)
  String password;
  // Name of the customer
  @Column(name = "name", nullable = false, updatable = true)
  String name;
  // Address of the customer
  @Column(name = "address", nullable = true, updatable = true)
  String address;

  // Role of the customer
  @Column(name = "role", nullable = true, updatable = true)
  @ValidateRole
  // Set<String> role;
  String role;

  // Gender of the customer
  @Column(name = "gender", nullable = false, updatable = true)
  boolean gender;
  @ValidateStatus
  @Column(name = "status")
  String status;
  // Birthday of the customer
  @Column(name = "create_at", nullable = false, updatable = false)
  Instant create;
  @LastModifiedDate
  @Column(name = "update_at", nullable = false, updatable = true)
  Instant update;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "birthday")
  Date birthDay;

  // Long product_id;
  // @OneToMany(mappedBy = "schema_customer", cascade = CascadeType.ALL)
  // @JsonIgnore
  // @JoinColumn
  // @JsonManagedReference
  // List<Product> products;

  public Customer() {
  }

  public Long getUserid() {
    return userid;
  }

  public void setUserid(Long userid) {
    this.userid = userid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public @NotNull @Size(min = 3, max = 20, message = "USERNAME_INVALID") String getUsername() {
    return username;
  }

  public void setUsername(@NotNull @Size(min = 3, max = 20, message = "USERNAME_INVALID") String username) {
    this.username = username;
  }

  public @Size(min = 8, message = "PASSWORD_INVALID") @NotBlank String getPassword() {
    return password;
  }

  public void setPassword(@Size(min = 8, message = "PASSWORD_INVALID") @NotBlank String password) {
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

  public Date getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }

  public Customer(Long userid, UUID uuid,
      @NotNull @Size(min = 3, max = 20, message = "USERNAME_INVALID") String username,
      @Size(min = 8, message = "PASSWORD_INVALID") @NotBlank String password, String name, String address, String role,
      boolean gender, String status, Instant create, Instant update, Date birthDay) {
    this.userid = userid;
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
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

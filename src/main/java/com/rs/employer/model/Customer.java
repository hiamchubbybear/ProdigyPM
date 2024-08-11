package com.rs.employer.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rs.employer.Validator.DateOfBirth.DobValidator;
import com.rs.employer.Validator.Status.ValidateStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity

@DynamicUpdate
@DynamicInsert
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @UuidGenerator
  private UUID uuid;
  // @NotNull
  @Size(min = 3, max = 20, message = "USERNAME_INVALID")
  @Column(name = "username", nullable = false, updatable = false)
  private String username;
  @Size(min = 8, message = "PASSWORD_INVALID")
  // @NotBlank
  @Column(nullable = false, updatable = true)
  private String password;
  @Column(nullable = false, updatable = true)
  private String name;
  @Column(nullable = true, updatable = true)
  private String address;
  @ManyToMany
  private Set<Role> roles;
  @Column(nullable = false, updatable = true)
  private boolean gender;
  @ValidateStatus
  @Column(name = "status")
  private String status;
  @Column(name = "create_at", nullable = false, updatable = false)
  private Instant create;
  @LastModifiedDate
  @Column(name = "update_at", nullable = false, updatable = true)
  private Instant update;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DobValidator(min = 18, message = "UNCATEGORIZE_EXCEPTION")
  @Column(name = "dob")
  @JsonIgnore
  private LocalDate dob;

  public Customer(UUID uuid, @NotNull @Size(min = 3, max = 20, message = "USERNAME_INVALID") String username,
      @Size(min = 8, message = "PASSWORD_INVALID") @NotBlank String password, String name, String address,
      Set<Role> roles, boolean gender, String status, Instant create, Instant update, LocalDate dob) {
    this.uuid = uuid;
    this.username = username;
    this.password = password;
    this.name = name;
    this.address = address;
    this.roles = roles;
    this.gender = gender;
    this.status = status;
    this.create = create;
    this.update = update;
    this.dob = dob;
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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
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

  public LocalDate getDob() {
    return dob;
  }

  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  public Customer() {
  }

}

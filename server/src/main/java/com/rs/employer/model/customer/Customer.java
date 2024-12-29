package com.rs.employer.model.customer;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rs.employer.validator.DateOfBirth.DobValidator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "customer")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "uuid", updatable = false, nullable = false, length = 36)
  private UUID uuid;
  @Size(min = 3, max = 20, message = "USERNAME_INVALID")
  @Column(name = "username", nullable = false, updatable = false)
  private String username;
  @Size(min = 8, message = "PASSWORD_INVALID")
  @Column(nullable = true)
  private String password;
  private String email;
  private String name;
  private String address;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
          name = "customer_roles", // Tên bảng trung gian
          joinColumns = @JoinColumn(name = "customer_id"), // Cột khóa chính của Customer
          inverseJoinColumns = @JoinColumn(name = "role_id") // Cột khóa chính của Role
  )
  private Set<Role> roles;
  private boolean gender;
  @Column(name = "status")
  private boolean status;
  @Column(name = "create_at", updatable = false)
  private Instant create;
  @LastModifiedDate
  @Column(name = "update_at")
  private Instant update;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @DobValidator(min = 18, message = "UNCATEGORIZE_EXCEPTION")
  @Column(name = "dob")
  @JsonIgnore
  private LocalDate dob;
  @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL , orphanRemoval = true)
  private Set<Token> token;
  @Lob
  @Column(name = "image", columnDefinition = "LONGBLOB")
  @Nullable
  @JsonIgnore
  private byte[] image;

  public Customer() {

  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public boolean isStatus() {
    return status;
  }


  public Set<Token> getToken() {
    return token;
  }

  public void setToken(Set<Token> token) {
    this.token = token;
  }

  public Customer(UUID uuid, String username, String password, String email, String name, String address, Set<Role> roles, boolean gender, boolean status, Instant create, Instant update, LocalDate dob, Set<Token> token, @Nullable byte[] image) {
    this.uuid = uuid;
    this.username = username;
    this.password = password;
    this.email = email;
    this.name = name;
    this.address = address;
    this.roles = roles;
    this.gender = gender;
    this.status = status;
    this.create = create;
    this.update = update;
    this.dob = dob;
    this.token = token;
    this.image = image;
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

  public Customer(UUID uuid, @NotNull @Size(min = 3, max = 20, message = "USERNAME_INVALID") String username, String email,
                  @Size(min = 8, message = "PASSWORD_INVALID") String password, String name, String address, Set<Role> roles,
                  boolean gender, boolean status, Instant create, Instant update, LocalDate dob) {
    this.uuid = uuid;
    this.username = username;
      this.email = email;
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

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
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

  public String getEmail() {
    return email;
  }

  public Customer(String username, String email, Instant create, String password) {
    this.username = username;
    this.email = email;
    this.create = create;
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}

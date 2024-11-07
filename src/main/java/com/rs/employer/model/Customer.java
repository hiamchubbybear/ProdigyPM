package com.rs.employer.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rs.employer.Validator.DateOfBirth.DobValidator;

import jakarta.validation.constraints.NotBlank;
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
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @UuidGenerator
  @Column(updatable = false)
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
  @ManyToMany
  private Set<Role> roles;
  private boolean gender;
  @Nullable
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
  @OneToOne
//   @JoinColumn(name = "cart", referencedColumnName = "id")
  private Cart cart;
  String resetToken;
  @Lob
  @Column(name = "image", columnDefinition = "LONGBLOB")
  @Nullable
  @JsonIgnore
  private byte[] image;

  public Customer(UUID uuid, String username, String password, String email, String name, String address, Set<Role> roles, boolean gender, boolean status, Instant create, Instant update, LocalDate dob, Cart cart, String resetToken, byte[] image) {
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
    this.cart = cart;
    this.resetToken = resetToken;
    this.image = image;
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

  public String getResetToken() {
    return resetToken;
  }

  public Customer(UUID uuid, String username, String password, String email, String name, String address, Set<Role> roles, boolean gender, boolean status, Instant create, Instant update, LocalDate dob, Cart cart, String resetToken) {
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
    this.cart = cart;
    this.resetToken = resetToken;
  }

  public void setResetToken(String resetToken) {
    this.resetToken = resetToken;
  }

  public Customer(UUID uuid, @NotNull @Size(min = 8, max = 20, message = "USERNAME_INVALID") String username, String email,
                  @Size(min = 8, message = "PASSWORD_INVALID") @NotBlank String password, String name, String address,
                  Set<Role> roles, boolean gender, boolean status, Instant create, Instant update, LocalDate dob) {
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
                  boolean gender, boolean status, Instant create, Instant update, LocalDate dob, Cart cart) {
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
    this.cart = cart;
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
  public Customer() {
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public Cart getCart() {
    return cart;
  }
  public void setCart(Cart cart) {
    this.cart = cart;
  }

}

package com.rs.employer.model;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(nullable = true, name = "create_At")
    Instant create;
    String owner;
    @Column(nullable = true, name = "update_At")
    Instant update;
    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    Customer customer;
    @OneToMany(mappedBy = "carts")
    @JsonIgnore
    Set<Product> products;
    public Cart(Instant create, String owner, Instant update) {

        this.create = create;
        this.owner = owner;
        this.update = update;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Cart(Long id, Instant create, Instant update) {
        this.id = id;
        this.create = create;
        this.update = update;
    }

    public Cart() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Cart(Long id, Instant create, String owner, Instant update, Customer customer, Set<Product> products) {
        this.id = id;
        this.create = create;
        this.owner = owner;
        this.update = update;
        this.customer = customer;
        this.products = products;
    }

}

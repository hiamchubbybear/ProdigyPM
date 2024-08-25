package com.rs.employer.model;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Entity(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    Long cartid;
    @Column(nullable = true, name = "create_At")
    Instant create;
    String owner;
    @Column(nullable = true, name = "update_At")
    Instant update;
    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    Customer customer;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=true , updatable = true)
    @JsonIgnore
    // @JoinTable(name = "cart_product")
    Set<Product> products;

    public Cart(Instant create, String owner, Instant update) {

        this.create = create;
        this.owner = owner;
        this.update = update;
        this.customer = customer;
    }

    public Long getId() {
        return cartid;
    }

    public void setId(Long id) {
        this.cartid = id;
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
        this.cartid = id;
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
        this.cartid = id;
        this.create = create;
        this.owner = owner;
        this.update = update;
        this.customer = customer;
        this.products = products;
    }

}

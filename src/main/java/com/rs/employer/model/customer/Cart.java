package com.rs.employer.model.customer;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.rs.employer.model.warehouse.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    @ManyToMany
    Set<Product> products;

    public Cart(Instant create, String owner, Instant update , Customer customer) {

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

    public Cart( Instant create, Instant update ,String owner) {
        this.create = create;
        this.update = update;
        this.owner = owner;
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


    public void setCartid(Long cartid) {
        this.cartid = cartid;
    }


    public Cart(Long cartid, Instant create, String owner, Instant update, Customer customer, Set<Product> products) {
        this.cartid = cartid;
        this.create = create;
        this.owner = owner;
        this.update = update;
        this.customer = customer;
        this.products = products;
    }

    public Long getCartid() {
        return cartid;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


}

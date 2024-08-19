package com.rs.employer.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Entity(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = true, name = "create_At")
    Instant create;
    String owner;
    @Column(nullable = true, name = "update_At")
    Instant update;
    // @OneToOne(mappedBy = "cart")
    // Customer customer;

    public Cart(Instant create, String owner, Instant update) {

        this.create = create;
        this.owner = owner;
        this.update = update;
        // this.customer = customer;
    }

    @ManyToMany
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

}

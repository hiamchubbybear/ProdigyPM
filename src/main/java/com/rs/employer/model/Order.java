package com.rs.employer.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
    private Instant createAt;
    private Instant updateAt;
    @ManyToOne
    @JoinColumn(name="ship_order" , nullable = false)
    private Shipment shipment;

    public Order(int id, Set<OrderItem> orderItems, Instant createAt, Instant updateAt, Shipment shipment) {
        this.id = id;
        this.orderItems = orderItems;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.shipment = shipment;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order() {
    }
}

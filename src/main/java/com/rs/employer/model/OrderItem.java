package com.rs.employer.model;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_id;
    private int quantity;
    private double price;
    @ManyToOne
    @JoinColumn(name="order_item", nullable=false)
    private Order order;

    public OrderItem(int order_id, int quantity, double price, Order order) {
        this.order_id = order_id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }


    public OrderItem() {
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

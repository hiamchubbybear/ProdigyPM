package com.rs.employer.model;

import jakarta.persistence.*;

import javax.naming.Name;

@Entity
@Table(name ="order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    public OrderItem(int id, int quantity, double price, Order order) {
        this.id = id;
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
        return id;
    }

    public void setOrder_id(int id) {
        this.id = id;
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

package com.rs.employer.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "shipments")
public class Shipment {
    @Id
    private int shipId;
    @OneToOne(mappedBy = "")
            Customer customerId;
    Date dateShip;
    String address;
    String city;
    String state;
    String zipCode;
    @OneToMany(mappedBy = "shipment")
    Set<Order> order;

    public Shipment(int shipId, Customer customerId, Date dateShip, String address, String city, String state, String zipCode, Set<Order> order) {
        this.shipId = shipId;
        this.customerId = customerId;
        this.dateShip = dateShip;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.order = order;
    }

    public Shipment() {
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Date getDateShip() {
        return dateShip;
    }

    public void setDateShip(Date dateShip) {
        this.dateShip = dateShip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }
}

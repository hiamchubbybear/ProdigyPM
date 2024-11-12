package com.rs.employer.model.warehouse;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventiontoryId;
    @ManyToOne
    @JoinColumn(name = "inventoryProduct")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "inventoryWarehouse")
    private WareHouse wareHouse;
    private BigDecimal  quantity;
    private Date lastUpdate;

    public Inventory(Long inventiontoryId, Product product, WareHouse wareHouse, BigDecimal quantity, Date lastUpdate) {
        this.inventiontoryId = inventiontoryId;
        this.product = product;
        this.wareHouse = wareHouse;
        this.quantity = quantity;
        this.lastUpdate = lastUpdate;
    }

    public Inventory() {
    }

    public Long getInventiontoryId() {
        return inventiontoryId;
    }

    public void setInventiontoryId(Long inventiontoryId) {
        this.inventiontoryId = inventiontoryId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

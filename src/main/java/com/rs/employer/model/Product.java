package com.rs.employer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Product class
@Entity
@Table(name = "schema_product")
public class Product {
    // ID of the product
    @Id
    // Auto increment by 1
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long ID;
    // Name of the product
    @Column(name = "name")
    private String name;
    // Expriation date of the product
    @Column(name = "exp")
    private String exp;
    // Sub category of the product
    @Column(name = "sub")
    private String sub;
    // Unit of the product
    @Column(name = "unit")
    private String unit;

    // Getters and Setters
    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Product() {
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    // Constructor
    public Product(Long iD, String name, String exp, String sub, String unit) {
        ID = iD;
        this.name = name;
        this.exp = exp;
        this.sub = sub;
        this.unit = unit;
    }

}

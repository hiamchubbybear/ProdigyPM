package com.rs.employer.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Product class
// @Data
// // @Setter
// // @Getter
// @AllArgsConstructor
// @NoArgsConstructor
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
    @Column(name = "name_product", nullable = false)
    private String name;
    // Type of the product
    @Column(name = "product_type", nullable = false)
    private String type;
    // Size and weight of the product
    @Column(name = "product_size", nullable = false)
    private Long size;
    // Weight of the product
    @Column(name = "product_weight", nullable = false)
    private Long weight;
    // Unit of the weight
    @Column(name = "product_weight_unit", nullable = false)
    private String weight_unit;
    // Unit of the size
    @Column(name = "product_size_", nullable = false)
    private String size_unit;
    // Expriation date of the product
    @Column(name = "expire_date_product", nullable = false)
    private Date exp;
    // Sub category of the product
    @Column(name = "subtitle_product", nullable = false)
    private String sub;
    // Unit of the product
    @Column(name = "unit_product", nullable = false)
    private String unit;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public Long getWeight() {
        return weight;
    }
    public void setWeight(Long weight) {
        this.weight = weight;
    }
    public String getWeight_unit() {
        return weight_unit;
    }
    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }
    public String getSize_unit() {
        return size_unit;
    }
    public void setSize_unit(String size_unit) {
        this.size_unit = size_unit;
    }
    public Date getExp() {
        return exp;
    }
    public void setExp(Date exp) {
        this.exp = exp;
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
    public Product(Long iD, String name, String type, Long size, Long weight, String weight_unit, String size_unit,
            Date exp, String sub, String unit) {
        ID = iD;
        this.name = name;
        this.type = type;
        this.size = size;
        this.weight = weight;
        this.weight_unit = weight_unit;
        this.size_unit = size_unit;
        this.exp = exp;
        this.sub = sub;
        this.unit = unit;
    }
    
}

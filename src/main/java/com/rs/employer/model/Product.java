package com.rs.employer.model;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
// @Data
// // @Setter
// // @Getter
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id" )
    Long productid;
    @Column(name = "name_product", nullable = false)
    String name;
    @Column(name = "product_type")
    String type;
    @Column(name = "product_size")
    Long size;
    @Column(name = "product_weight")
    Long weight;
    @Column(name = "product_weight_unit")
    String weight_unit;
    @Column(name = "product_size_unit")
    String size_unit;
    @Column(name = "expire_date_product")
    Date exp;
    @Column(name = "create_at", nullable = false, updatable = false)
    Instant create;
    @Column(name = "update_at", nullable = false, updatable = true)
    Instant update;
    @Column(name = "subtitle_product")
    String sub;
    @Column(name = "unit_product", nullable = false)
    String unit;
    @ManyToMany
    @JsonIgnore
    Set<Cart> carts;

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    // Long customer_id;
    // @ManyToOne
    // @JsonIgnore
    // @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    // public Customer customer;
    public Long getProduct_id() {
        return productid;
    }

    public void setProduct_id(Long product_id) {
        this.productid = product_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Product(Long product_id, String name, String type, Long size, Long weight, String weight_unit,
            String size_unit, Date exp, Instant create, Instant update, String sub, String unit) {
        this.productid = product_id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.weight = weight;
        this.weight_unit = weight_unit;
        this.size_unit = size_unit;
        this.exp = exp;
        this.create = create;
        this.update = update;
        this.sub = sub;
        this.unit = unit;
    }


    public Product(Long productid, String name, String type, Long size, Long weight, String weight_unit,
            String size_unit, Date exp, Instant create, Instant update, String sub, String unit, Set<Cart> carts) {
        this.productid = productid;
        this.name = name;
        this.type = type;
        this.size = size;
        this.weight = weight;
        this.weight_unit = weight_unit;
        this.size_unit = size_unit;
        this.exp = exp;
        this.create = create;
        this.update = update;
        this.sub = sub;
        this.unit = unit;
        this.carts = carts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}

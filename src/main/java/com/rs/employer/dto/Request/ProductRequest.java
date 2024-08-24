package com.rs.employer.dto.Request;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// Product class
// @Data
// // @Setter
// // @Getter
// @AllArgsConstructor
// @NoArgsConstructor
public class ProductRequest {
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long product_id;
    String name;
    String type;
    Long size;
    Long weight;
    String weight_unit;
    String size_unit;
    Date exp;
    Instant create;
    Instant update;
    String sub;

    public ProductRequest(Long product_id, String name, String type, Long size, Long weight, String weight_unit,
            String size_unit, Date exp, Instant create, Instant update, String sub, String unit) {
        this.product_id = product_id;
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

    public ProductRequest() {
    }

    String unit;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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

}

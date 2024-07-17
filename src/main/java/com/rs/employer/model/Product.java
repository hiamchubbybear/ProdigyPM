package com.rs.employer.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;
    @Column(name = "name_product", nullable = false)
    private String name;
    @Column(name = "product_type", nullable = false)
    private String type;
    @Column(name = "product_size", nullable = false)
    private Long size;
    @Column(name = "product_weight", nullable = false)
    private Long weight;
    @Column(name = "product_weight_unit", nullable = false)
    private String weight_unit;
    @Column(name = "product_size_unit", nullable = false)
    private String size_unit;
    @Column(name = "expire_date_product", nullable = false)
    private Date exp;
    @Column(name = "create_at", nullable = false, updatable = false)
    private Instant create;
    @Column(name = "update_at", nullable = false, updatable = true)
    private Instant update;
    @Column(name = "subtitle_product", nullable = false)
    private String sub;
    @Column(name = "unit_product", nullable = false)
    private String unit;
    @ManyToMany(mappedBy = "products")
    private List<Customer> posts = new ArrayList<>();

    public Long getID() {
        return id;
    }

    public Product(Long id, String name, String type, Long size, Long weight, String weight_unit, String size_unit,
            Date exp, Instant create, Instant update, String sub, String unit, List<Customer> posts) {
        this.id = id;
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
        this.posts = posts;
    }

    public Product() {
    }

    public void setID(Long iD) {
        id = iD;
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
        id = iD;
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

    public List<Customer> getPosts() {
        return posts;
    }

    public void setPosts(List<Customer> posts) {
        this.posts = posts;
    }

}

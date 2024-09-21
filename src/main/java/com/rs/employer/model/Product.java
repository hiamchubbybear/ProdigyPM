package com.rs.employer.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String brand;
private BigDecimal price;
private int inventory;
private String description;
@ManyToOne( cascade= CascadeType.ALL )
@JoinColumn(name = "category_id")
private Category category;
private Instant createAt;
private Instant updateAt;
@OneToMany(mappedBy = "products" , targetEntity = Image.class)
@JsonIgnore
private List<Image> images;
@ManyToMany
@JsonIgnore
private Set<Cart> carts;
@ManyToOne
private Resources resources;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getInventory() {
        return inventory;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
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
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public Set<Cart> getCarts() {
        return carts;
    }
    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }
    public Product() {
    }
    public Product(Long id, String name, String brand, BigDecimal price, int inventory, String description,
            Category category, Instant createAt, Instant updateAt, List<Image> images, Set<Cart> carts) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.images = images;
        this.carts = carts;
    }


}

package com.rs.employer.dto.Request.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.rs.employer.model.Image;

import jakarta.persistence.OneToMany;

// Product class
// @Data
// // @Setter
// // @Getter
// @AllArgsConstructor
// @NoArgsConstructor
public class ProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private String cateId;
    @OneToMany(mappedBy = "products" , targetEntity = Image.class)
    private List<Long> images;
    Instant createAt;
    Instant updateAt;
    Set<Long> carts;
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
    public String getCate_id() {
        return cateId;
    }
    public void setCate_id(String cate_id) {
        this.cateId = cate_id;
    }
    public List<Long> getImages() {
        return images;
    }
    public void setImages(List<Long> images) {
    this.images = images;
    }
    public Set<Long> getCarts() {
        return carts;
    }
    public void setCarts(Set<Long> carts) {
        this.carts = carts;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
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

    public ProductRequest(Long id, String name, String brand, BigDecimal price, int inventory, String description,
            String cateId, List<Long> images, Instant createAt, Instant updateAt, Set<Long> carts) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.cateId = cateId;
        this.images = images;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.carts = carts;
    }

}

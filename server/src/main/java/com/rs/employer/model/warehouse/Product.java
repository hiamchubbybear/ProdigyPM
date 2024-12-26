package com.rs.employer.model.warehouse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.rs.employer.model.manufacture.BOM;
import com.rs.employer.model.others.Category;
import com.rs.employer.model.others.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String brand;
    private BigDecimal price;
    private Integer quantity;
    private Integer status;
    @OneToMany(mappedBy = "product")
    private Set<Inventory> inventories;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;
    private Instant createAt;
    private Instant updateAt;
    @OneToMany(mappedBy = "products", targetEntity = Image.class)
    @JsonIgnore
    private List<Image> images;
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resources resources;
    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productOrders;
    @OneToMany(mappedBy = "product")
    // Bill of material
    private Set<BOM> bom;


    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
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

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Set<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    public Set<BOM> getBom() {
        return bom;
    }

    public void setBom(Set<BOM> bom) {
        this.bom = bom;
    }

    public Product(Long productId, String productName, String brand, BigDecimal price, Integer quantity, Integer status, Set<Inventory> inventories, String description, Category category, Instant createAt, Instant updateAt, List<Image> images, Resources resources, Set<ProductOrder> productOrders, Set<BOM> bom) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.inventories = inventories;
        this.description = description;
        this.category = category;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.images = images;
        this.resources = resources;
        this.productOrders = productOrders;
        this.bom = bom;
    }
}

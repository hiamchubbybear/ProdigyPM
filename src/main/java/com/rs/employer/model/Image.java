package com.rs.employer.model;

import java.sql.Blob;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileType;
    private String typeName;
    private String downloadUrl;
    private Instant createAt;
    private Instant updateAt;
    @Lob
    private Blob image;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product products;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getDownloadUrl() {
        return downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
    public Blob getImage() {
        return image;
    }
    public void setImage(Blob image) {
        this.image = image;
    }
    public Product getProducts() {
        return products;
    }
    public void setProducts(Product products) {
        this.products = products;
    }
    public Image() {
    }
    public Image(Long id, String fileType, String typeName, String downloadUrl, Instant createAt, Instant updateAt,
            Blob image, Product products) {
        this.id = id;
        this.fileType = fileType;
        this.typeName = typeName;
        this.downloadUrl = downloadUrl;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.image = image;
        this.products = products;
    }

}

package com.rs.employer.model.others;

import java.sql.Blob;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.rs.employer.model.warehouse.Product;
import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private String fileType;
    private String fileName;
    private String downloadUrl;
    private Instant createAt;
    private Instant updateAt;
    @Lob
    private Blob image;
    @ManyToOne
    @JoinColumn(name = "product_image")
    @JsonIgnore
    private Product products;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public Image(UUID id, String fileType, String fileName, String downloadUrl, Instant createAt, Instant updateAt, Blob image, Product products) {
        this.id = id;
        this.fileType = fileType;
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.image = image;
        this.products = products;
    }

    public Image() {
    }

}

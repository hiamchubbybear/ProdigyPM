package com.rs.employer.dto.Request;

import org.springframework.context.annotation.Configuration;

import java.time.DateTimeException;
import java.time.Instant;

@Configuration
public class ImageDTO {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
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

    Instant createAt;
    Instant updateAt;

    public ImageDTO(Long imageId, String imageName, String downloadUrl, Instant createAt, Instant updateAt) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.downloadUrl = downloadUrl;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Long getImageId() {
        return imageId;
    }
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public ImageDTO() {
    }

//    public ImageDTO(String downloadUrl, String imageName, Long imageId) {
//        this.downloadUrl = downloadUrl;
//        this.imageName = imageName;
//        this.imageId = imageId;
//    }
}

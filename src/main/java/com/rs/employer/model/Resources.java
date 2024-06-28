package com.rs.employer.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Data
// @Setter
// @Getter
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@Table(name = "schema_resources")
public class Resources {
    // ID of the product
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    // Name of the product
    @Column(name = "resources_titles", nullable = true)
    private String titles;
    // Locale of the product
    @Column(name = "locale", nullable = false)
    private String locale;
    // ID resources of the product
    @Column(name = "product_resources_id", nullable = false)
    private String resources_id;
    // Create date of the product
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "create_at", nullable = false)
    private Date create;
    // Update date of the product
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "update_at", nullable = false)
    private Date update;
    public Date now = new Date();

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getResources_id() {
        return resources_id;
    }

    public void setResources_id(String resources_id) {
        this.resources_id = resources_id;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public Resources(Long iD, String titles, String locale,
            String resources_id,
            Date create,
            Date update) {
        ID = iD;
        this.titles = titles;
        this.locale = locale;
        this.resources_id = resources_id;
        this.create = create;
        this.update = update;
    }

    public Resources() {
    }

}

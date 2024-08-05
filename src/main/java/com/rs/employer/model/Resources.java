package com.rs.employer.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "schema_resources")
public class Resources {
    // ID of the product
    @Id
    @Column(name = "Resourceid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resourceid;
    // Name of the product
    @Column(name = "Resources_Name", nullable = true)
    String name;
    @Column(name = "Resources_Titles", nullable = true)
    String titles;
    // Locale of the product
    @Column(name = "Resources_Locale", nullable = false)
    String locale;
    // ID resources of the product
    @Column(name = "Resources_Company", nullable = true)
    String company;
    // Create date of the product
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "Create_At")
    Instant create;

    public Instant getCreate() {
        return this.create;
    }

    public void setCreate(Instant create) {
        this.create = create;
    }

    // Update date of the product
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "Update_At")
    Instant update;

    public Instant getUpdate() {
        return this.update;
    }

    public void setUpdate(Instant update) {
        this.update = update;
    };

    public Long getResourceid() {
        return resourceid;
    }

    public void setResourceid(Long resourceid) {
        this.resourceid = resourceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Resources(Long resourceid, String name, String titles, String locale, String company, Instant create,
            Instant update) {
        this.resourceid = resourceid;
        this.name = name;
        this.titles = titles;
        this.locale = locale;
        this.company = company;
        this.create = create;
        this.update = update;
    }

}

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
    @Column(name = "Resource_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    // Name of the product
    @Column(name = "Resources_Name", nullable = true)
    private String name;
    @Column(name = "Resources_Titles", nullable = true)
    private String titles;
    // Locale of the product
    @Column(name = "Resources_Locale", nullable = false)
    private String locale;
    // ID resources of the product
    @Column(name = "Resources_Company", nullable = true)
    private String company;
    // Create date of the product
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "Create_At", nullable = false)
    private Date create;
    // Update date of the product
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "Update_At", nullable = false)
    private Date update;
    public Date now = new Date();
    public Resources(Long iD, String name, String titles, String locale, String company, Date create, Date update,
            Date now) {
        ID = iD;
        this.name = name;
        this.titles = titles;
        this.locale = locale;
        this.company = company;
        this.create = create;
        this.update = update;
        this.now = now;
    }
    public Resources() {
    }
    public Long getID() {
        return ID;
    }
    public void setID(Long iD) {
        ID = iD;
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
    public Date getNow() {
        return now;
    }
    public void setNow(Date now) {
        this.now = now;
    }

   

}

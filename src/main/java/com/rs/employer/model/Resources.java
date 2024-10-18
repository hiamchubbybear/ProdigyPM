package com.rs.employer.model;

import java.time.Instant;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "resources")
public class Resources {
    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resourceid;
    String name;
    String titles;
    String locale;
    String company;
    @Column(name = "create_at", nullable = true)
    Instant create;
    @OneToMany(mappedBy = "resources")
    private Set<Product> products;
    public Instant getCreate() {
        return this.create;
    }
    public void setCreate(Instant create) {
        this.create = create;
    }
    public Resources() {
    }
    @Column(name = "update_at", nullable = true)
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

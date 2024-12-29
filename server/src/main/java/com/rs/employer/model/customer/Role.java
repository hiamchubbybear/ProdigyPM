package com.rs.employer.model.customer;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.cdi.Eager;

@Entity
public class Role  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> permissions;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role(int id, String name, String description, Set<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Role() {

    }

    public Role(String name, String description, Set<Permission> permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}

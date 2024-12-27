package com.rs.employer.model.customer;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.data.redis.core.RedisHash;

@Entity
public class Role  implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    private String name;
    private String description;
    @ManyToMany
    private Set<Permission> permissions;

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

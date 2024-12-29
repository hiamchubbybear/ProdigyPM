package com.rs.employer.dto.Request.Auth;

import java.util.Set;

public class RoleRequest {
    private Integer id;
    private String name;
    private String description;
    private Set<String> permissions;

    public RoleRequest() {
    }

    public RoleRequest(Integer id, String name, String description, Set<String> permissions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}

package com.rs.employer.dto.Response;

import java.util.Set;

public class RoleRespone {
    private String name;
    private String description;
    private Set<PermissionRespone> permissions;

    public RoleRespone(String name, String description, Set<PermissionRespone> permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
    }

    public Set<PermissionRespone> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionRespone> permissions) {
        this.permissions = permissions;
    }

    public RoleRespone() {
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
}

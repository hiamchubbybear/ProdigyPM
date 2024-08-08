package com.rs.employer.dto.Request;

public class PermissionRequest {
    private String name;
    private String description;

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

    public PermissionRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

}

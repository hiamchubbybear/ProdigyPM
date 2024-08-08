package com.rs.employer.dto.Respone;

public class RoleRespone {
    private String name;
    private String description;

    public RoleRespone(String name, String description) {
        this.name = name;
        this.description = description;
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

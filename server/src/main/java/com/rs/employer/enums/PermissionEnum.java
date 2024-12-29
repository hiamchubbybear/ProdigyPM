package com.rs.employer.enums;

public enum PermissionEnum {

    // Admin
    PERMIT_ALL("PERMIT_ALL", "All Permissions", "ADMIN"),
    DELETE_MS("DELETE_MS", "Delete Management System", "ADMIN"),
    ADD_USER("ADD_USER", "Add User", "ADMIN"),
    UPDATE_RESOURCES("UPDATE_RESOURCES", "Update Resources", "ADMIN"),
    DELETE_RESOURCES("DELETE_RESOURCES", "Delete Resources", "ADMIN"),
    ADD_RESOURCES("ADD_RESOURCES", "Add Resources", "ADMIN"),
    UPDATE_ROLE("UPDATE_ROLE", "Update Role", "ADMIN"),
    DELETE_ROLE("DELETE_ROLE", "Delete Role", "ADMIN"),
    ADD_ROLE("ADD_ROLE", "Add Role", "ADMIN"),
    UPDATE_PERMISSION("UPDATE_PERMISSION", "Update Permission", "ADMIN"),
    DELETE_PERMISSION("DELETE_PERMISSION", "Delete Permission", "ADMIN"),
    ADD_PERMISSION("ADD_PERMISSION", "Add Permission", "ADMIN"),

    // User
    UPDATE_USER("UPDATE_USER", "Update User", "USER"),

    // Vendor
    UPDATE_PRODUCT("UPDATE_PRODUCT", "Update Product", "VENDOR"),
    DELETE_PRODUCT("DELETE_PRODUCT", "Delete Product", "VENDOR"),
    ADD_PRODUCT("ADD_PRODUCT", "Add Product", "VENDOR");










    private final String name;
    private final String description;
    private final String assigned;

    PermissionEnum(String name, String description, String assigned) {
        this.name = name;
        this.description = description;
        this.assigned = assigned;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAssigned() {
        return assigned;
    }
}
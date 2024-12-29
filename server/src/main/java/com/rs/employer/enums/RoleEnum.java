package com.rs.employer.enums;


public enum RoleEnum {
    ADMIN("ADMIN" , "This role for admin") ,
    USER ("USER" , "This role for user") ,
    VENDOR("VENDOR" , "This role for vendor") ,;


    public String name;
    public String description;

    RoleEnum() {

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

    RoleEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

package com.rs.employer.model.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission  implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    private String name;
    private String description;
    private String assigned;
    public Permission(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public Permission(String name, String description, String assigned) {
        this.name = name;
        this.description = description;
        this.assigned = assigned;
    }

    public String getName() {
        return this.name;
    }

    public Permission() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

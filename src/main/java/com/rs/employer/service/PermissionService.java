package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Permission;

@Service
public interface PermissionService {
    public Permission addPermission(Permission permission);

    public Permission updatePermission(Permission permission);

    public Boolean deletePermission(String permission);

    public List<Permission> allPermission();
}

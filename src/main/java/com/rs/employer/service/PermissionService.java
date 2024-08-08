package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.PermissionRequest;
import com.rs.employer.model.Permission;

@Service
public interface PermissionService {
    public Permission addPermission(PermissionRequest permission);

    public Permission updatePermission(PermissionRequest permission);

    public Boolean deletePermission(String permission);

    public List<Permission> allPermission();
}

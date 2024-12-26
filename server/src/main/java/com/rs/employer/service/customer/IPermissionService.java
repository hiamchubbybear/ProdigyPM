package com.rs.employer.service.customer;

import  java.util.List;

import com.rs.employer.model.customer.Permission;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.Auth.PermissionRequest;



@Service
public interface IPermissionService {
    public Permission addPermission(PermissionRequest permission);

    public Permission updatePermission(PermissionRequest permission);

    public Boolean deletePermission(String permission);

    public List<Permission> allPermission();
}

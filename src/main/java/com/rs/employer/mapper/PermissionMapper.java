package com.rs.employer.mapper;

import com.rs.employer.model.customer.Permission;
import org.mapstruct.Mapper;

import com.rs.employer.dto.Request.Auth.PermissionRequest;
import com.rs.employer.dto.Response.PermissionRespone;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionRespone toPermissionRespone(PermissionRequest request);

    Permission toPermission(PermissionRequest request);

}

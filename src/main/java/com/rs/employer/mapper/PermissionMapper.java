package com.rs.employer.mapper;

import org.mapstruct.Mapper;

import com.rs.employer.dto.Request.PermissionRequest;
import com.rs.employer.dto.Respone.PermissionRespone;
import com.rs.employer.model.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionRespone toPermissionRespone(PermissionRequest request);

    Permission toPermission(PermissionRequest request);

}

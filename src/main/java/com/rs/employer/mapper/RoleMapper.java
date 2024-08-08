package com.rs.employer.mapper;

import org.mapstruct.Mapper;

import com.rs.employer.dto.Request.RoleRequest;
import com.rs.employer.dto.Respone.RoleRespone;
import com.rs.employer.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleRespone toRoleRespone(RoleRequest request);

    Role toRole(RoleRequest request);

}

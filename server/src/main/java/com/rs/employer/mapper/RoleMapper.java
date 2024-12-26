package com.rs.employer.mapper;

import com.rs.employer.model.customer.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.dto.Request.Auth.RoleRequest;
import com.rs.employer.dto.Response.RoleRespone;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleRespone toRoleRespone(Role request);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

}

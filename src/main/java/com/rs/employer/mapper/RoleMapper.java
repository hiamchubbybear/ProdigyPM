package com.rs.employer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.dto.Request.Auth.RoleRequest;
import com.rs.employer.dto.Respone.RoleRespone;
import com.rs.employer.model.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleRespone toRoleRespone(Role request);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

}

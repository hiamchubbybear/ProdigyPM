package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.RoleRequest;
import com.rs.employer.dto.Respone.RoleRespone;
import com.rs.employer.model.Role;

@Service
public interface RoleService {
    public RoleRespone addRole(RoleRequest role);

    public RoleRespone updateRole(RoleRequest role);

    public Boolean deleteRole(String role);

    public List<Role> allRole();
}

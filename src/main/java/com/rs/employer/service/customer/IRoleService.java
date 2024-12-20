package com.rs.employer.service.customer;

import java.util.List;

import com.rs.employer.model.customer.Role;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.Auth.RoleRequest;
import com.rs.employer.dto.Response.RoleRespone;
@Service
public interface IRoleService {
    public RoleRespone addRole(RoleRequest role);

    public RoleRespone updateRole(RoleRequest role);

    public Boolean deleteRole(String role);

    public List<Role> allRole();
}

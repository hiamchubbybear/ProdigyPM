package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Role;

@Service
public interface RoleService {
    public Role addRole(Role role);

    public Role updateRole(Role role);

    public Boolean deleteRole(String role);

    public List<Role> allRole();
}

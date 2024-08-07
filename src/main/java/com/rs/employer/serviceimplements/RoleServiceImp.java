package com.rs.employer.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Role;
import com.rs.employer.repository.RoleRepository;
import com.rs.employer.service.RoleService;

public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository repo;

    @Override
    public Role addRole(Role role) {
        if (repo.existsById(role.getName()))
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        else
            return repo.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        if (repo.existsById(role.getName())) {
            return repo.save(role);
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    public Boolean deleteRole(String role) {
        if (repo.existsById(role)) {
            repo.deleteById(role);
            return true;
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    public List<Role> allRole() {
        List<Role> list = repo.findAll();
        return list;
    }

}

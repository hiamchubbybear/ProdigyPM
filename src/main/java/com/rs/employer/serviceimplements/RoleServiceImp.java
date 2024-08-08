package com.rs.employer.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.RoleRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.RoleMapper;
import com.rs.employer.model.Role;
import com.rs.employer.repository.RoleRepository;
import com.rs.employer.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository repo;
    @Autowired
    RoleMapper mapper;

    @Override
    public Role addRole(RoleRequest request) {
        if (repo.existsById(request.getName()))
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        else {
            Role role = mapper.toRole(request);
            return repo.save(role);
        }
    }

    @Override
    public Role updateRole(RoleRequest request) {
        if (repo.existsById(request.getName())) {
            Role role = mapper.toRole(request);
            return repo.save(role);
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    public Boolean deleteRole(String request) {
        if (repo.existsById(request)) {
            repo.deleteById(request);
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

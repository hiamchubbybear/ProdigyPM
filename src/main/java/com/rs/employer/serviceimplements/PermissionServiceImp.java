package com.rs.employer.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Permission;
import com.rs.employer.repository.PermissionRepository;
import com.rs.employer.service.PermissionService;

public class PermissionServiceImp implements PermissionService {
    @Autowired
    PermissionRepository repo;

    @Override
    public Permission addPermission(Permission permission) {
        if (repo.existsById(permission.getName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        } else
            return repo.save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        if (repo.existsById(permission.getName())) {
            return repo.save(permission);
        }
        throw new AppException(ErrorCode.RUNTIME_ERROR);
    }

    @Override
    public Boolean deletePermission(String permission) {
        if (repo.existsById(permission)) {
            repo.deleteById(permission);
            return true;
        }
        throw new AppException(ErrorCode.RUNTIME_ERROR);

    }

    @Override
    public List<Permission> allPermission() {
        List<Permission> list = repo.findAll();
        return list;
    }
}

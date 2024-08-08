package com.rs.employer.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.PermissionRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.PermissionMapper;
import com.rs.employer.model.Permission;
import com.rs.employer.repository.PermissionRepository;
import com.rs.employer.service.PermissionService;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    PermissionRepository repo;
    @Autowired
    PermissionMapper mapper;

    @Override
    public Permission addPermission(PermissionRequest request) {
        if (repo.existsById(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        } else {
            Permission permission = mapper.toPermission(request);
            return repo.save(permission);
        }
    }

    @Override
    public Permission updatePermission(PermissionRequest request) {
        if (repo.existsById(request.getName())) {
            Permission permission = mapper.toPermission(request);
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

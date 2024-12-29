package com.rs.employer.service.customer.customer;

import java.util.List;


import com.rs.employer.model.customer.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.customer.PermissionRepository;
import com.rs.employer.dto.Request.Auth.PermissionRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.PermissionMapper;
import com.rs.employer.service.customer.IPermissionService;

@Service
public class PermissionService implements IPermissionService {
    private final PermissionRepository repo;
    private final PermissionMapper mapper;
    @Autowired
    public PermissionService(PermissionRepository repo, PermissionMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
//    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Permission addPermission(PermissionRequest request) {
        if (repo.existsById(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        } else {
            Permission permission = mapper.toPermission(request);
            return repo.save(permission);
        }
    }

    @Override
//    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Permission updatePermission(PermissionRequest request) {
        if (repo.existsById(request.getName())) {
            Permission permission = mapper.toPermission(request);
            return repo.save(permission);
        }
        throw new AppException(ErrorCode.RUNTIME_ERROR);
    }

    @Override
//    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deletePermission(String permission) {
        if (repo.existsById(permission)) {
            repo.deleteById(permission);
            return true;
        }
        throw new AppException(ErrorCode.RUNTIME_ERROR);

    }

    @Override
//    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Permission> allPermission() {
        List<Permission> list = repo.findAll();
        return list;
    }
}

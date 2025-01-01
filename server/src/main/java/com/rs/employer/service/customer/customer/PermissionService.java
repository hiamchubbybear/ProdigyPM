package com.rs.employer.service.customer.customer;

import java.util.List;


import com.rs.employer.model.customer.Permission;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    /**
     * @permission: Account owner and admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to create a new permission.
     * @parameters: A `PermissionRequest` object containing the details of the permission to be created.
     * @description: This method checks if a permission with the same name already exists. If it does, it throws an `AppException` with the `ErrorCode.PRODUCT_ALREADY_EXISTS` code. Otherwise, it maps the `PermissionRequest` object to a `Permission` object and saves the new permission to the database.
     * @return: The newly created `Permission` object.
     * @throws AppException: If a permission with the same name already exists.
     */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @CacheEvict(value = "permissions", key = "'allPermissions'")
    public Permission addPermission(PermissionRequest request) {
        if (repo.existsById(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_ALREADY_EXISTS);
        } else {
            Permission permission = mapper.toPermission(request);
            return repo.save(permission);
        }
    }

    /**
     * @throws AppException: If a permission with the provided name is not found.
     * @permission: Account owner and admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to update an existing permission.
     * @parameters: A `PermissionRequest` object containing the updated details of the permission.
     * @description: This method checks if a permission with the provided name exists. If it does, it updates the permission details in the database using the provided `PermissionRequest` object. Otherwise, it throws an `AppException` with the `ErrorCode.RUNTIME_ERROR` code.
     * @return: The updated `Permission` object.
     */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Caching(evict =
            {
                    @CacheEvict(value = "permissions", key = "'allPermissions'"),
                    @CacheEvict(value = "roles", key = "'allRoles'")
            }
    )
    public Permission updatePermission(PermissionRequest request) {
        if (repo.existsById(request.getName())) {
            Permission permission = mapper.toPermission(request);
            return repo.save(permission);
        }
        throw new AppException(ErrorCode.RUNTIME_ERROR);
    }

    /**
     * @throws AppException: If a permission with the provided name is not found.
     * @permission: Account owner and admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to delete a permission.
     * @parameters: The name of the permission to be deleted.
     * @description: This method checks if a permission with the provided name exists. If it does, it deletes the permission from the database. Otherwise, it throws an `AppException` with the `ErrorCode.RUNTIME_ERROR` code.
     * @return: True if the permission is deleted successfully, false otherwise.
     */

    @Override
    @CacheEvict(value = "permissions", key = "'allPermissions'")
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deletePermission(String permission) {
        if (repo.existsById(permission)) {
            repo.deleteById(permission);
            return true;
        }
        throw new AppException(ErrorCode.RUNTIME_ERROR);

    }

    /**
     * @throws AppException: Not thrown by this method.
     * @permission: Account owner and admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to retrieve all permissions.
     * @parameters: None.
     * @description: This method retrieves all permissions from the database.
     * @return: A list of all `Permission` objects.
     */
    @Override
    @Cacheable(value = "permissions", key = "'allPermissions'")
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Permission> allPermission() {
        return repo.findAll();
    }
}

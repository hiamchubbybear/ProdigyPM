package com.rs.employer.service.customer.customer;

import java.util.HashSet;
import java.util.List;


import com.rs.employer.mapper.RoleMapper;
import com.rs.employer.model.customer.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.customer.PermissionRepository;
import com.rs.employer.dao.customer.RoleRepository;
import com.rs.employer.dto.Request.Auth.RoleRequest;
import com.rs.employer.dto.Response.RoleRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.service.customer.IRoleService;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper mapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository, RoleMapper mapper) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.mapper = mapper;
    }

    /**
     * @throws AppException: - Code: `ErrorCode.ROLE_NOT_FOUND` - if any of the permission IDs in the request are not found in the database.
     * @permission: Admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to create a new role.
     * @parameters: A `RoleRequest` object containing the details of the role to be created, including its name and a list of permission IDs.
     * @description: This method first maps the `RoleRequest` object to a `Role` object. Then, it retrieves all permission objects from the database whose IDs match the IDs provided in the `request.getPermissions()` method. These permissions are then set to the `role` object. Finally, the new role is saved to the database and a `RoleResponse` object representing the created role is returned.
     * @return: A `RoleResponse` object containing the details of the newly created role.
     * @caching: - Evicts caches containing all permissions ("permissions" with key "'allPermissions'") and all roles ("roles" with key "'allRoles'") to ensure data consistency after creating a new role.
     */
    @Override
    @Caching(evict =
            {
                    @CacheEvict(value = "permissions", key = "'allPermissions'"),
                    @CacheEvict(value = "roles", key = "'allRoles'")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public RoleRespone addRole(RoleRequest request) {
        var role = mapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = roleRepository.save(role);
        return mapper.toRoleRespone(role);
    }

    /**
     * @throws AppException: - Code: `ErrorCode.ROLE_NOT_FOUND` - if the role with the provided ID is not found in the database.
     * @permission: Admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to update an existing role.
     * @parameters: A `RoleRequest` object containing the updated details of the role, including its ID, name, and (optionally) a list of permission IDs.
     * @description: This method first checks if a role with the provided ID exists in the database. If it does, it maps the `RoleRequest` object to a `Role` object. The permissions for the role can be updated by providing a new list of permission IDs in the request. Finally, the updated role is saved to the database and a `RoleResponse` object representing the updated role is returned.
     * @return: A `RoleResponse` object containing the details of the updated role.
     * @caching: - Evicts caches containing all permissions ("permissions" with key "'allPermissions'") and all roles ("roles" with key "'allRoles'") to ensure data consistency after updating a role.
     */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Caching(evict =
            {
                    @CacheEvict(value = "permissions", key = "'allPermissions'"),
                    @CacheEvict(value = "roles", key = "'allRoles'")
            }
    )
    public RoleRespone updateRole(RoleRequest request) {
        if (roleRepository.existsById(request.getId())) {
            Role role = mapper.toRole(request);
            return mapper.toRoleRespone(roleRepository.save(role));
        }
        throw new AppException(ErrorCode.ROLE_NOT_FOUND);
    }

    /**
     * @throws AppException: - Code: `ErrorCode.ROLE_NOT_FOUND` - if the role with the provided ID is not found in the database.
     * @permission: Admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to delete a role.
     * @parameters: The ID of the role to be deleted.
     * @description: This method checks if a role with the provided ID exists in the database. If it does, the role is deleted from the database and `true` is returned.
     * @return: True if the role is deleted successfully, false otherwise.
     * @caching: - Evicts caches containing all permissions ("permissions" with key "'allPermissions'") and all roles ("roles" with key "'allRoles'") to ensure data consistency after deleting a role.
     */
    @Override
    @Caching(evict =
            {
                    @CacheEvict(value = "permissions", key = "'allPermissions'"),
                    @CacheEvict(value = "roles", key = "'allRoles'")
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deleteRole(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        throw new AppException(ErrorCode.ROLE_NOT_FOUND);
    }

    /**
     * @throws: None
     * @permission: Admin (**SCOPE_PERMIT_ALL**).
     * @function: Used to list all roles.
     * @parameters: None
     * @description: List all roles
     * @return: List of roles
     * @caching: - Caching all roles
     */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Cacheable(value = "permissions", key = "'allPermissions'")
    public List<Role> allRole() {
        return roleRepository.findAll();
    }

}

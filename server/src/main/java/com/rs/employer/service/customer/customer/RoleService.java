package com.rs.employer.service.customer.customer;

import java.util.HashSet;
import java.util.List;


import com.rs.employer.mapper.RoleMapper;
import com.rs.employer.model.customer.Role;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public RoleRespone addRole(RoleRequest request) {
        var role = mapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = roleRepository.save(role);
        return mapper.toRoleRespone(role);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public RoleRespone updateRole(RoleRequest request) {
        if (roleRepository.existsById(request.getId())) {
            Role role = mapper.toRole(request);

            return mapper.toRoleRespone(roleRepository.save(role));
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Boolean deleteRole(Integer id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
//    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Role> allRole() {
        List<Role> lists = roleRepository.findAll();
        return lists;
    }

}

package com.rs.employer.serviceimplements;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.RoleRequest;
import com.rs.employer.dto.Respone.RoleRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.RoleMapper;
import com.rs.employer.model.Role;
import com.rs.employer.repository.PermissionRepository;
import com.rs.employer.repository.RoleRepository;
import com.rs.employer.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleMapper mapper;

    @Override
    public RoleRespone addRole(RoleRequest request) {
        // if (roleRepository.existsById(request.getName()))
        // throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        // else {
        var role = mapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = roleRepository.save(role);
        return mapper.toRoleRespone(role);
        // }
    }

    @Override
    public RoleRespone updateRole(RoleRequest request) {
        if (roleRepository.existsById(request.getName())) {
            Role role = mapper.toRole(request);

            return mapper.toRoleRespone(roleRepository.save(role));
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    public Boolean deleteRole(String request) {
        if (roleRepository.existsById(request)) {
            roleRepository.deleteById(request);
            return true;
        }
        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    public List<Role> allRole() {
        List<Role> list = roleRepository.findAll();
        return list;
    }

}

package com.rs.employer.controller.auth;

import java.util.List;

import com.rs.employer.dao.customer.RoleRepository;
import com.rs.employer.dto.Response.RoleRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.customer.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.RoleRequest;

import com.rs.employer.service.customer.customer.RoleService;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class RoleController {
    @Autowired
    RoleService svc;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/all")
    public ApiRespone<List<Role>> getAllRole() {

        return new ApiRespone<>(svc.allRole());
    }

    @GetMapping("/{role}")
    public ApiRespone<Role> getAllRole(@PathVariable Integer role) {
        return new ApiRespone<>(roleRepository.findById(role).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
    }

    @PutMapping("/update")
    public ApiRespone<RoleRespone> updateRole(@RequestBody RoleRequest role) {

        return new ApiRespone<>(svc.updateRole(role));
    }

    @PostMapping("/add")
    public ApiRespone<RoleRespone> addRole(@RequestBody RoleRequest role) {

        return new ApiRespone<>(svc.addRole(role));
    }

    @DeleteMapping("/delete/{role}")
    public ApiRespone<Boolean> deleteRole(@PathVariable("role") Integer role) {
        return new ApiRespone<>(svc.deleteRole(role));
    }

}

package com.rs.employer.controller.auth;

import java.util.List;

import com.rs.employer.dao.customer.RoleRepository;
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
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.allRole());
        return apiRespone;
    }

    @GetMapping("/{role}")
    public ApiRespone<Role> getAllRole(@PathVariable Integer role) {
        return new ApiRespone<>(roleRepository.findById(role).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
    }

    @PutMapping("/update")
    public ApiRespone<Role> updateRole(@RequestBody RoleRequest role) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.updateRole(role));
        return apiRespone;
    }

    @PostMapping("/add")
    public ApiRespone<Role> addRole(@RequestBody RoleRequest role) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.addRole(role));
        return apiRespone;
    }

    @DeleteMapping("/delete/{role}")
    public ApiRespone<Boolean> deleteRole(@PathVariable("role") Integer role) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.deleteRole(role));
        return apiRespone;
    }

}

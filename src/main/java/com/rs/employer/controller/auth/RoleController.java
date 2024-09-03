package com.rs.employer.controller.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.RoleRequest;
import com.rs.employer.model.Role;
import com.rs.employer.serviceimplements.RoleService;
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService svc;

    @GetMapping("/all")
    public ApiRespone<List<Role>> getAllRole() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.allRole());
        return apiRespone;
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
    public ApiRespone<Boolean> deleteRole(@PathVariable("role") String role) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.deleteRole(role));
        return apiRespone;
    }
}

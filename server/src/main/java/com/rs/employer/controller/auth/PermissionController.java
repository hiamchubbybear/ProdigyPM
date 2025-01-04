package com.rs.employer.controller.auth;

import java.util.List;

import com.rs.employer.model.customer.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.PermissionRequest;

import com.rs.employer.service.customer.customer.PermissionService;

@RestController
// Test test khu khu
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    PermissionService svc;

    @GetMapping("/all")
    public ApiRespone<List<Permission>> getAllPermission() {
        return new ApiRespone<>((svc.allPermission()));
    }

    @PutMapping("/update")
    public ApiRespone<Permission> updatePermission(@RequestBody PermissionRequest permission) {
        return new ApiRespone<>((svc.updatePermission(permission)));
    }

    @PostMapping("/add")
    public ApiRespone<Permission> addPermission(@RequestBody PermissionRequest permission) {
        return new ApiRespone<>((svc.addPermission(permission)));
    }

    @DeleteMapping("/delete/{permission}")
    public ApiRespone<Boolean> deletePermission(@PathVariable("permission") String permission) {
        return new ApiRespone<>((svc.deletePermission(permission)));
    }
}

package com.rs.employer.controller.auth;

import java.util.List;

import com.rs.employer.model.customer.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.PermissionRequest;

import com.rs.employer.serviceimplements.PermissionService;

@RestController

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    PermissionService svc;

    @GetMapping("/all")
    public ApiRespone<List<Permission>> getAllPermission() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.allPermission());
        return apiRespone;
    }

    @PutMapping("/update")
    public ApiRespone<Permission> updatePermission(@RequestBody PermissionRequest permission) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.updatePermission(permission));
        return apiRespone;
    }

    @PostMapping("/add")
    public ApiRespone<Permission> addPermission(@RequestBody PermissionRequest permission) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.addPermission(permission));
        return apiRespone;
    }

    @DeleteMapping("/delete/{permission}")
    public ApiRespone<Boolean> deletePermission(@PathVariable("permission") String permission) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.deletePermission(permission));
        return apiRespone;
    }
}

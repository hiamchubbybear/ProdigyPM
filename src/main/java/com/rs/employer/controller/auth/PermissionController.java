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
import com.rs.employer.dto.Request.PermissionRequest;
import com.rs.employer.model.Permission;
import com.rs.employer.serviceimplements.PermissionServiceImp;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    PermissionServiceImp svc;

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

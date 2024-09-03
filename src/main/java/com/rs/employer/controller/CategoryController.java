package com.rs.employer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.model.Category;
import com.rs.employer.service.CategoryService;

@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService svc;

    @GetMapping("/all")
    public ApiRespone<Category> getAllCategory() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.allCategory());
        return apiRespone;
    }

    @PutMapping("/update")
    public ApiRespone<Category> updateCategory(@RequestBody Category role) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.updateCategory(role));
        return apiRespone;
    }

    @PostMapping("/add")
    public ApiRespone<Category> addCategory(@RequestBody Category request) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.addCategory(request));
        return apiRespone;
    }

    @DeleteMapping("/delete/{role}")
    public ApiRespone<Boolean> deleteCategory(@PathVariable("role") Long role) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(svc.deleteCategory(role));
        return apiRespone;
    }
}

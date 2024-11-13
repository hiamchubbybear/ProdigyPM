package com.rs.employer.controller.warehouse;

import com.rs.employer.model.others.Category;
import com.rs.employer.service.others.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CategoryController {
    @Autowired
    private ICategoryService svc;

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
        svc.deleteCategory(role);
        apiRespone.setData(true);
        return apiRespone;
    }

    @GetMapping("/getbyname")
    public ApiRespone<Category> getCategoryByName(@PathVariable String name) {
        ApiRespone apiRespone = new ApiRespone<>(svc.getCategoryByName(name));
        return apiRespone;
    }
}

package com.rs.employer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Product;
import com.rs.employer.repository.ProductRepository;
import com.rs.employer.service.ProductService;
//Controller for product
@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin
class Productcontroller {
    @Autowired
    private ProductService productServiceImplement;
    @Autowired
    private ProductRepository repo;

    // List product by ID
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Product> getUserById(@PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(productServiceImplement.getProduct(ID));
        return apiRespone;
    }

    // List all product in database
    @GetMapping(path = "/all")
    public ApiRespone<List<Product>> getAllProduct(Product product) {
        List<Product> list = productServiceImplement.getAllProduct();
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(list);
        return apiRespone;
    }

    // Add product
    @PostMapping(path = "/add")
    public ApiRespone<Boolean> addProduct(@RequestBody Product product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(productServiceImplement.addProduct(product));
        return apiRespone;
    }

    // Delete product by ID
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<String> deleteProduct(@PathVariable(name = "id") Long ID) {
        if (repo.existsById(ID) && productServiceImplement.deleteProduct(ID)) {
            ApiRespone apiRespone = new ApiRespone<>();
            apiRespone.setData("User deleted");
            return apiRespone;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    // Update product by ID
    @PutMapping(path = "/update/{id}")
    public ApiRespone<String> changeProduct(@PathVariable(name = "id", required = true) Long ID,
            @RequestBody Product product) {
        if (repo.findById(ID).isPresent()) {
            ApiRespone apiRespone = new ApiRespone<>();
            apiRespone.setData(productServiceImplement.updateProduct(ID, product));
            return apiRespone;
        } else
            throw new AppException(ErrorCode.USER_NOTFOUND);
    }
}

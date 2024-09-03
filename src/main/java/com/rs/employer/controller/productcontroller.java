package com.rs.employer.controller;

import java.util.List;
import java.util.Optional;

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
import com.rs.employer.dto.Request.ProductRequest;
import com.rs.employer.model.Product;
import com.rs.employer.serviceimplements.ProductService;


@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin
public class ProductController {
    private ProductService repo;

    // List product by ID
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Optional<Product>> getUserById(@PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProduct(ID));
        return apiRespone;
    }


    // List all product in database
    @GetMapping(path = "/all")
    public ApiRespone<Optional<Product>> getAllProduct(Product product) {
        List<Product> list = repo.getAllProduct();
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(list);
        return apiRespone;
    }

    // Add product
    @PostMapping(path = "/add")
    public ApiRespone<Boolean> addProduct(@RequestBody ProductRequest product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.addProduct(product));
        return apiRespone;
    }

    // Delete product by ID
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteProductByID(@PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        repo.deleteProduct(ID);
        apiRespone.setData(true);
        return apiRespone;
    }

    // Update product by ID
    @PutMapping(path = "/update/{id}")
    public ApiRespone<String> changeProduct(@PathVariable(name = "id", required = true) Long ID,
            @RequestBody ProductRequest product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.updateProduct(ID, product));
        return apiRespone;
    }
    @GetMapping(path = "/getbycategory/{name}")
    public ApiRespone<List<Product>> getProductByCategory(@PathVariable(name = "id") String name) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByCategory(name));
        return apiRespone;
    }
}

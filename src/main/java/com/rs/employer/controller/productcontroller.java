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

import com.rs.employer.model.Product;
import com.rs.employer.repository.ProductRepository;
import com.rs.employer.service.ProductService;

//Controller for product
@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin
public class productcontroller {
    @Autowired
    private ProductService productServiceImplement;
    @Autowired
    private ProductRepository repo;

    // List product by ID
    @GetMapping(path = "/getbyid/{id}")
    public Product getUserById(@PathVariable(name = "id") Long ID) {
        return productServiceImplement.getProduct(ID);
    }

    // List all product in database
    @GetMapping(path = "/all")
    public List<Product> getAllProduct(Product product) {
        List<Product> list = productServiceImplement.getAllProduct();
        return list;
    }

    // Add product
    @PostMapping(path = "/add")
    public Boolean addProduct(@RequestBody Product product) {
        return productServiceImplement.addProduct(product);
    }

    // Delete product by ID
    @DeleteMapping(path = "/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long ID) {
        if (repo.existsById(ID) && productServiceImplement.deleteProduct(ID))
            return "User deleted ";
        else
            return "User can't delete";
    }

    // Update product by ID
    @PutMapping(path = "/update/{id}")
    public String changeProduct(@PathVariable(name = "id", required = true) Long ID,
            @RequestBody Product product) {
        if (repo.findById(ID).isPresent()) {
            return productServiceImplement.updateProduct(ID, product);
        } else
            throw new NullPointerException("Product" + product.getID() + " is not exist in database ");
    }
}

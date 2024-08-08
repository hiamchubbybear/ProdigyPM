package com.rs.employer.service;

import java.util.List;
import java.util.Optional;

import com.rs.employer.dto.Request.ProductRequest;
import com.rs.employer.model.Product;

public interface ProductService {
    // List all product
    public List<Product> getAllProduct();

    // Add product
    public Product addProduct(ProductRequest product);

    // Update product by ID
    public Product updateProduct(Long id, ProductRequest product);

    // Delete product by ID
    public Boolean deleteProduct(Long id);

    // List product by ID
    public Optional<Product> getProduct(Long id);

    // Register user
    // public Product registerUser(String username, String password, String login);
}

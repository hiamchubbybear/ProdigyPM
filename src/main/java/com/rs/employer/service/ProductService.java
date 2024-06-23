package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Product;

// Service interface for product
@Service
public interface ProductService {
    // List all product
    public List<Product> getAllProduct();

    // Add product
    public Boolean addProduct(Product product);

    // Update product by ID
    public String updateProduct(Long id, Product product);

    // Delete product by ID
    public Boolean deleteProduct(Long id);

    // List product by ID
    public Product getProduct(Long id);

    // Register user
    // public Product registerUser(String username, String password, String login);
}

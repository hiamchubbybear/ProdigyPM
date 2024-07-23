package com.rs.employer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Product;

// Service interface for product
@Service
public interface ProductService {
    // List all product
    public List<Product> getAllProduct();

    // Add product
    public Product addProduct(Product product);

    // Update product by ID
    public String updateProduct(Long id, Product product);

    // Delete product by ID
    public Boolean deleteProduct(Long id);

    // List product by ID
    public Optional<Product> getProduct(Long id);

    // Register user
    // public Product registerUser(String username, String password, String login);
}

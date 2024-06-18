package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Product;

@Service
public interface ProductService {
    public List<Product> getAllProduct();

    public Boolean addProduct(Product product ) ;

    public String updateProduct(Long id, Product product);

    public Boolean deleteProduct(Long id);

    public Product getProduct(Long id);

    // public Product registerUser(String username, String password, String login);
}

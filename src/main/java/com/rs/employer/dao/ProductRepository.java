package com.rs.employer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Product;

// Database JPA repository for Product
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Product findByProductId(Long productid);
    // Set<Product> findAllByProductid();
    // Set<Product>
    List<Product> findByCategoryName(String name);
    Boolean existsByName(String name);
}

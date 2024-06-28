package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Product;

// Database JPA repository for Product
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

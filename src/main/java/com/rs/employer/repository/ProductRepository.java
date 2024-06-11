package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

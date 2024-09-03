package com.rs.employer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Product;

// Database JPA repository for Product
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String name);
    Boolean existsByName(String name);
    List<Product> findByBrandId(Long id);
    List<Product> findByName(String name);
    public List<Product> findByBrandIdAndCategoryId(Long brandId, Long categoryId);
    Long countByBrandIdAndName(Long brandId, String name);
    List<Product> findByBrandIdAndName(Long brandId, String name);
    List<Product> findByBrandIdAndInventory(Long brandId, int inventory);
}

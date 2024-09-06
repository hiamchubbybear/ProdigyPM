package com.rs.employer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Product;

// Database JPA repository for Product
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategoryName(String name);
    public Boolean existsByName(String name);
    public List<Product> findByBrand(String brand);
    public List<Product> findByName(String name);
    public List<Product> findByBrandAndCategoryName(String brand, String name);
    public Long countByBrandAndName(String brand, String name);
    public List<Product> findByBrandAndName(String brand, String name);
    public List<Product> findByBrandAndInventory(String brand, Long inventory);
    public boolean existsByCategoryName(String name);
}

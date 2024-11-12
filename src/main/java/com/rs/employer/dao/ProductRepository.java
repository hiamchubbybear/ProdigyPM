package com.rs.employer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.warehouse.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategoryCategoryName(String name);
    public Boolean existsByProductName(String name);
    public List<Product> findByBrand(String brand);
    public List<Product> findByProductName(String name);
    public List<Product> findByBrandAndCategory_CategoryName(String brand, String name);
    public Long countByBrandAndProductName(String brand, String name);
    public List<Product> findByBrandAndProductName(String brand, String name);
    @Query("SELECT p FROM Product p JOIN p.inventories i WHERE p.brand = :brand AND i.inventiontoryId = :inventoryId")
    List<Product> findByBrandAndInventories(@Param("brand") String brand, @Param("inventoryId") Long inventoryId);
    public boolean existsByCategoryCategoryName(String name);
    public Product findByProductId(Long productId);
}

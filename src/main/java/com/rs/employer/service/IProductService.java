package com.rs.employer.service;

import java.util.List;
import java.util.Optional;

import com.rs.employer.dto.Request.Product.ProductRequest;
import com.rs.employer.model.Product;

public interface IProductService {
    public List<Product> getAllProduct();
    public Product addProduct(ProductRequest product);
    public Product updateProduct(Long id, ProductRequest product);
    public void deleteProduct(Long id);
    public Optional<Product> getProduct(Long id);
    public List<Product> getProductByCategory(String name);
    public List<Product> getProductByBrand(String brand);
    public List<Product> getProductByName(String name);
    public List<Product> getProductByBrandAndCategory(String brandId, Long categoryId);
    public List<Product> getProductByBrandAndInventory(String brandId, Long inventory);
    public List<Product> getProductByBraindAndName(String brandId, String name);
    public Long countProductByBrandAndName(String brandId , String name);
}

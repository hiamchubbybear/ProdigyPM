package com.rs.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Resources;

@Service
public interface ResourcesService {
    // List all product
    public List<Resources> getAllProductByResourcesID();

    // Add product
    public Resources addProductResources(Resources product);

    // Update product by ID
    public String updateProductResources(Long id, Resources product);

    // Delete product by ID
    public Boolean deleteProductResources(Long id);

    // List product by ID
    public Resources getProductResourcesByID(Long rID);
}

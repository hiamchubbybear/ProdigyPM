package com.rs.employer.service;

import java.util.List;

import com.rs.employer.dto.Request.Resources.ResourcesRequest;
import com.rs.employer.model.warehouse.Resources;

public interface IResourcesService {
    public List<Resources> getAllProductByResourcesID();

    public Resources addProductResources(ResourcesRequest product);

    public Resources updateProductResources(ResourcesRequest product);

    public Boolean deleteProductResources(Long id);
}

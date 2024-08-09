package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.ResourcesRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ResourcesMapper;
import com.rs.employer.model.Resources;
import com.rs.employer.repository.ProductRepository;
import com.rs.employer.repository.ResourcesRepo;
import com.rs.employer.service.ResourcesService;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private ResourcesRepo repository;
    private Instant date;
    @Autowired
    private ProductRepository repo;
    @Autowired
    ResourcesMapper mapper;

    @Override
    public List<Resources> getAllProductByResourcesID() {
        return repository.findAll();
    }

    @Override
    public Resources addProductResources(ResourcesRequest request) {
        Resources resources = mapper.toResources(request);
        resources.setCreate(date.now());
        resources.setUpdate(date.now());
        return repository.save(resources);
    }

    @Override
    public Resources updateProductResources(ResourcesRequest request) {
        if (!repo.existsById(request.getResourceid())) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        } else {
            Resources resources = mapper.toResources(request);
            resources.setUpdate(date.now());
            return repository.save(resources);
        }
    }

    @Override
    public Boolean deleteProductResources(Long id) {
        Optional<Resources> resourcesList = repository.findById(id);
        if (resourcesList.isPresent()) {
            repository.deleteById(id);
            return true;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    public Resources getProductResourcesByID(Long rID) {
        Optional<Resources> eOptional = repository.findById(rID);
        Resources product = eOptional.get();
        return product;
    }

}

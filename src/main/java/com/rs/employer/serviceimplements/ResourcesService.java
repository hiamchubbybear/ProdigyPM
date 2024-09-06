package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.ProductRepository;
import com.rs.employer.dao.ResourcesRepo;
import com.rs.employer.dto.Request.Resources.ResourcesRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ResourcesMapper;
import com.rs.employer.model.Resources;
import com.rs.employer.service.IResourcesService;

@Service
public class ResourcesService implements IResourcesService {
    private ResourcesRepo repository;
    private Instant now = Instant.now();
    private ProductRepository repo;
    ResourcesMapper mapper;

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_ADD_RESOURCES')")
    public List<Resources> getAllProductByResourcesID() {
        return repository.findAll();
    }
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_ADD_RESOURCES')")
    public Resources addProductResources(ResourcesRequest request) {
        Resources resources = mapper.toResources(request);
        resources.setCreate(now);
        resources.setUpdate(now);
        return repository.save(resources);
    }
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_UPDATE_RESOURCES')")
    public Resources updateProductResources(ResourcesRequest request) {
        if (!repo.existsById(request.getResourceid())) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        } else {
            Resources resources = mapper.toResources(request);
            resources.setUpdate(now);
            return repository.save(resources);
        }
    }
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_DELETE_RESOURCES')")
    public Boolean deleteProductResources(Long id) {
        Optional<Resources> resourcesList = repository.findById(id);
        if (resourcesList.isPresent()) {
            repository.deleteById(id);
            return true;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_GET_RESOURCES')")
    public Resources getProductResourcesByID(Long rID) {
        Optional<Resources> eOptional = repository.findById(rID);
        Resources product = eOptional.get();
        return product;
    }
}

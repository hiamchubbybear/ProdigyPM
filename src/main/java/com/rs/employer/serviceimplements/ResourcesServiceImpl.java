package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
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

    @Override
    public List<Resources> getAllProductByResourcesID() {
        return repository.findAll();
    }

    @Override
    public Resources addProductResources(Resources product) {
        product.setCreate(date.now());
        product.setUpdate(date.now());
        return repository.save(product);
    }

    @Override
    public String updateProductResources(Long pID, Resources product) {
        Optional<Resources> productList = repository.findById(pID);
        Resources product1 = productList.get();
        if (product1.equals(pID)) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        } else {
            product1.setTitles(product.getTitles());
            product1.setLocale(product.getLocale());
            product1.setResourceid(pID);
            product1.setCreate(product.getCreate());
            product1.setUpdate(date.now());
            return "Succed";
        }
    }

    @Override
    public Boolean deleteProductResources(Long id) {
        Optional<Resources> resourcesList = repository.findById(id);
        if (resourcesList.isPresent()) {
            repository.deleteById(id);
            return true;
        } else
            throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    public Resources getProductResourcesByID(Long rID) {
        Optional<Resources> eOptional = repository.findById(rID);
        Resources product = eOptional.get();
        return product;
    }

}

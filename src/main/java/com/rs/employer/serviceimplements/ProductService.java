package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.ProductRepository;
import com.rs.employer.dto.Request.Product.ProductRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ProductMapper;
import com.rs.employer.model.Product;
import com.rs.employer.service.IProductService;

import lombok.extern.slf4j.Slf4j;

// Service implement for product

@Slf4j
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    Instant now = Instant.now();
    @Autowired
    ProductMapper mapper;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public Product addProduct(ProductRequest request) {
        if (!productRepository.existsByName(request.getName())) {
            Product product = mapper.toProduct(request);

            return productRepository.save(product);
        } else
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
    }

    @PreAuthorize("hasAuthority('SCOPE_DELETE_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public void deleteProduct(Long ID) {
            productRepository.findById(ID).ifPresentOrElse(product -> productRepository.delete(product) , () -> {
                throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
            });
        }

    @PreAuthorize("hasAuthority('SCOPE_UPDATE_PRODUCT') or hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public Product updateProduct(Long ID, ProductRequest request) {
        if (!productRepository.existsById(ID)) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        } else {
            Product product = mapper.toProduct(request);
            return productRepository.save(product);
        }

    }

    @Override
    public Optional<Product> getProduct(Long ID) {
        if (productRepository.existsById(ID))
            return productRepository.findById(ID);
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    public List<Product> getProductByCategory(String name) {
        return productRepository.findByCategoryName(name);
    }

    @Override
    public List<Product> getProductByBrand(String brain) {
        return productRepository.findByBrand(brain);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return  productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndCategory(String brand, Long categoryId) {
        return productRepository.findByBrandAndCategoryId(brand, categoryId);
    }

    @Override
    public List<Product> getProductByBrandAndInventory(String brand, Long inventory) {
        return productRepository.findByBrandAndInventory(brand, inventory);
    }

    @Override
    public List<Product> getProductByBraindAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}

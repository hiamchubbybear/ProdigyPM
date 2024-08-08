package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.ProductRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ProductMapper;
import com.rs.employer.model.Product;
import com.rs.employer.repository.ProductRepository;
import com.rs.employer.service.ProductService;

// Service implement for product
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    Instant date;
    @Autowired
    ProductMapper mapper;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(ProductRequest request) {
        if (!productRepository.existsById(request.getProduct_id())) {
            request.setCreate(date.now());
            request.setUpdate(date.now());
            Product product = mapper.toProduct(request);
            return productRepository.save(product);
        } else
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
    }

    @Override
    public Boolean deleteProduct(Long ID) {
        if (!productRepository.existsById(ID))
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
        else {
            productRepository.deleteById(ID);
            return true;
        }
    }

    @Override
    public Product updateProduct(Long ID, ProductRequest request) {
        if (!productRepository.existsById(ID)) {
            throw new NullPointerException("User is not exist ");
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

}
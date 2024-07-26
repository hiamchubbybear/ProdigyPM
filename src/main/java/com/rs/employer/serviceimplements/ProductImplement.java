package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Product;
import com.rs.employer.repository.ProductRepository;
import com.rs.employer.service.ProductService;

// Service implement for product
@Service
public class ProductImplement implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    Instant date;

    // Add product
    @Override
    public Product addProduct(Product product) {
        if (!productRepository.existsById(product.getProduct_id())) {
            product.setCreate(date.now());
            product.setUpdate(date.now());
            return productRepository.save(product);
        } else
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
    }

    // List product by ID
    @Override
    public Optional<Product> getProduct(Long ID) {
        if (productRepository.existsById(ID))
            return productRepository.findById(ID);
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    // Delete product by ID
    @Override
    public Boolean deleteProduct(Long ID) {
        if (!productRepository.existsById(ID))
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
        else {
            productRepository.deleteById(ID);
            return true;
        }
    }

    // List all product
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // Update product by ID
    @Override
    public String updateProduct(Long ID, Product product) {
        if (!productRepository.existsById(ID)) {
            throw new NullPointerException("User is not exist ");
        } else {
            product.setProduct_id(ID);
            product.setType(product.getType());
            product.setSize(product.getSize());
            product.setWeight(product.getWeight());
            product.setWeight_unit(product.getWeight_unit());
            product.setSize_unit(product.getSize_unit());
            product.setExp(product.getExp());
            product.setName(product.getName());
            product.setSize(product.getSize());
            product.setSub(product.getSub());
            product.setUpdate(date.now());
            productRepository.save(product);
            return "Your new products are change to a new ID is :" + ID;
        }
    }

}
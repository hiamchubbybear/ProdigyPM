package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.model.Product;
import com.rs.employer.repository.ProductRepository;
import com.rs.employer.service.ProductService;

// Service implement for product
@Service
class ProductImplement implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    Instant date;
    // Instant.now(Clock.system(ZoneId.of("Asia/HoChiMinh"))).truncatedTo(ChronoUnit.SECONDS);

    // Add product
    @Override
    public Boolean addProduct(Product product) {
        product.setCreate(date.now());
        product.setUpdate(date.now());
        productRepository.save(product);
        return true;
    }

    // List product by ID
    @Override
    public Product getProduct(Long ID) {
        Optional<Product> eOptional = productRepository.findById(ID);
        Product product = eOptional.get();
        return product;
    }

    // Delete product by ID
    @Override
    public Boolean deleteProduct(Long ID) {
        Optional<Product> eOptional = productRepository.findById(ID);
        Product product = eOptional.get();
        if (product.getID().equals(null))
            return false;
        else {
            productRepository.deleteById(ID);
            return true;

        }
    }

    // List all product
    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    // Update product by ID
    @Override
    public String updateProduct(Long ID, Product product) {
        Optional<Product> eOptional = productRepository.findById(ID);
        Product product1 = eOptional.get();
        if (product1.getID().equals(null))
            throw new NullPointerException("User is not exist ");
        else {

            productRepository.deleteById(ID);
            product.setUpdate(date.now());
            productRepository.save(product);
            return "Your new products are change to a new ID is :" + ID;
        }
    }

}
package com.rs.employer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.model.Product;
import com.rs.employer.repository.ProductRepository;

@Service
class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Boolean addProduct(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public Product getProduct(Long ID) {
        Optional<Product> eOptional = productRepository.findById(ID);
        Product product = eOptional.get();
        return product;
    }

    @Override
    public Boolean deleteProduct(Long ID) {
        Optional<Product> eOptional = productRepository.findById(ID);
        Product product = eOptional.get();
        if (product.getID().equals(null))
            return false;
        else
            return true;

    }

    @Override
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public String updateProduct(Long ID, Product product) {
        Optional<Product> eOptional = productRepository.findById(ID);
        Product product1 = eOptional.get();
        if (product1.getID().equals(null))
            throw new NullPointerException("User is not exist ");
        else {
            productRepository.deleteById(ID);
            productRepository.save(product);
            return "Your new products are change to a new ID is :" + ID;
        }
    }
        
}
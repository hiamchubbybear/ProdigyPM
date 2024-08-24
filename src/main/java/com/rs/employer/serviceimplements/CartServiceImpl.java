package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CartRepository;
import com.rs.employer.dao.ProductRepository;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Cart;

// Service implement for cart

@Service
public class CartServiceImpl {
    @Autowired
    private CartRepository cartRepository;
    Instant date;
    @Autowired
    private ProductRepository productRepository;


    @Autowired

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public Cart addCart(Cart request) {
        if (!cartRepository.existsById(request.getId())) {
            request.setCreate(date.now());
            request.setUpdate(date.now());
            // Set<Product> products = new HashSet<Product>(productRepository.findAllBy());
            // request.setProducts(products);
            return cartRepository.save(request);
        } else
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
    }

    // @PreAuthorize("hasAuthority('SCOPE_DELETE_PRODUCT')or
    // hasAuthority('SCOPE_PERMIT_ALL')")

    public Boolean deleteCart(Long ID) {
        if (!cartRepository.existsById(ID))
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
        else {
            cartRepository.deleteById(ID);
            return true;
        }
    }

    public Cart updateCart(Long ID, Cart request) {
        if (!cartRepository.existsById(ID)) {
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
        } else {
            return cartRepository.save(request);
        }

    }

    public Optional<Cart> getCart(Long ID) {
        if (cartRepository.existsById(ID))
            return cartRepository.findById(ID);
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

}

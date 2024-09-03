package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CartRepository;
import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dao.ProductRepository;
import com.rs.employer.dto.Request.Product.ProductCartRequest;
import com.rs.employer.dto.Request.Product.ProductCartRequestOne;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ProductMapper;
import com.rs.employer.model.Cart;
import com.rs.employer.model.Product;
@Service
public class ProductCartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepo customerRepository;
    Instant now = Instant.now();
    @Autowired
    ProductMapper mapper;

    public Cart addProductToCart(ProductCartRequest request) {
        // var data = SecurityContextHolder.getContext().getAuthentication().getName();
        Cart cart = cartRepository.findByCartid(request.getCart_id());
        Set<Product> ads = new HashSet<>();
        List<Long> ids = new ArrayList<>();
        List<Long> pds = new ArrayList<>();
        pds.addAll(cartRepository.findProductsByCartId(request.getCart_id()));
        for (Long long1 : pds) {
            System.err.println(long1);
        }
        // request.getProducts_id().forEach(System.out::println);
        for (Long long1 : request.getProducts_id()) {
            if(pds.contains(long1)) throw new AppException(ErrorCode.PRODUCT_EXISTED);
            ids.add(long1);
        }

        ads.addAll(productRepository.findAllById(ids) );
        cart.setProducts(ads);
        return cartRepository.save(cart);
    }
    public Cart addOneProductToCart(ProductCartRequestOne request) {
        Cart cart = cartRepository.findByCartid(request.getCart_id());
        return cartRepository.save(cart);
    }
    public Cart deleteProductFromCart(ProductCartRequest request ) {
        Cart cart = cartRepository.findByCartid(request.getCart_id());
        cartRepository.deleteAllById(request.getProducts_id());
        return cartRepository.save(cart);
    }
}

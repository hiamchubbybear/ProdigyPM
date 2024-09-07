package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CartRepository;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Cart;
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    Instant date= Instant.now();
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }
    public Cart addCart(Cart request) {
        if (!cartRepository.existsById(request.getId())) {
            request.setCreate(date);
            request.setUpdate(date);
            return cartRepository.save(request);
        } else
            throw new AppException(ErrorCode.CART_NOT_FOUNT);
    }
    public Boolean deleteCart(Long ID) {
        cartRepository.findById(ID).ifPresentOrElse(cartRepository::delete, () -> new AppException(ErrorCode.CART_NOT_FOUNT));
        return true;
    }
    public Cart updateCart(Long ID, Cart request) {
        if (!cartRepository.existsById(ID)) {
            throw new AppException(ErrorCode.CART_NOT_FOUNT);
        } else {
            return cartRepository.save(request);
        }
    }
    public Optional<Cart> getCart(Long ID) {
        if (cartRepository.existsById(ID))
            return cartRepository.findById(ID);
        else
            throw new AppException(ErrorCode.CART_NOT_FOUNT);
    }
}

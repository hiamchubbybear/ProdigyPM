package com.rs.employer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Product.ProductCartRequest;
import com.rs.employer.model.Cart;
import com.rs.employer.serviceimplements.CartService;
import com.rs.employer.serviceimplements.ProductCartService;
import com.rs.employer.serviceimplements.ProductService;


//Controller for cart
@RestController
@RequestMapping(path = "/api/cart")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CartController {
    private CartService repo;
    private ProductCartService pcRepository;
    private ProductService service;
    // List cart by ID
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Optional<Cart>> getUserById(@PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getCart(ID));
        return apiRespone;
    }

    // List all cart in database
    @GetMapping(path = "/all")
    public ApiRespone<Optional<Cart>> getAllCart(Cart cart) {
        List<Cart> list = repo.getAllCart();
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(list);
        return apiRespone;
    }

    // Add cart
    @PostMapping(path = "/add")
    public ApiRespone<Boolean> addCart(@RequestBody Cart cart) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.addCart(cart));
        return apiRespone;
    }

    // Delete cart by ID
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteCartByID(@PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.deleteCart(ID));
        return apiRespone;
    }

    // Update cart by ID
    @PutMapping(path = "/update/{id}")
    public ApiRespone<String> changeCart(@PathVariable(name = "id", required = true) Long ID,
            @RequestBody Cart cart) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.updateCart(ID, cart));
        return apiRespone;
    }
    @PostMapping(path = "/products")
    public ApiRespone<Cart> addProducts(@RequestBody ProductCartRequest request ) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(pcRepository.addProductToCart(request));
        return apiRespone;
    }
}

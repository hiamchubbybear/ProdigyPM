package com.rs.employer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.model.Product;
import com.rs.employer.service.ProductService;

@RestController
@RequestMapping(path = "/v1/m1")
public class productcontroller {
    @Autowired
    private ProductService productServiceImplement;

    @GetMapping(path = "/getmerid")
    public Product getUserById(@RequestParam(name = "ID") Long ID) {
        return productServiceImplement.getProduct(ID);
    }

    @GetMapping(path = "/getallmer")
    public List<Product> getAllProduct(Product product) {
        List<Product> list = productServiceImplement.getAllProduct();
        return list;
    }

    @PostMapping(path = "/addmer")
    public Boolean addProduct(@RequestBody Product product) {
        return productServiceImplement.addProduct(product);
    }

    @DeleteMapping(path = "/deletemer")
    public String deleteProduct(@RequestParam(name = "ID") Long ID) {
        if (productServiceImplement.deleteProduct(ID))

            return "User deleted ";
        else
            return "User can't delete";
    }

    @PutMapping(path = "/change")
    public String changeProduct(@RequestParam(name = "ID", required = true) Long ID,
            @RequestBody Product product) {
        return productServiceImplement.updateProduct(ID, product);
    }

}

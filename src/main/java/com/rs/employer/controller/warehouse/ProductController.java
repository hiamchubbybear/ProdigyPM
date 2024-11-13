package com.rs.employer.controller.warehouse;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rs.employer.dto.Request.Product.ProductRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.warehouse.Product;
import com.rs.employer.serviceimplements.warehouse.ProductService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping(path = "/api/product")
public class ProductController {
    @Autowired
    private ProductService repo;
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Optional<Product>> getUserById(@PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProduct(ID).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_EXISTED)));
        return apiRespone;
    }
    @GetMapping(path = "/all")
    public ApiRespone<Optional<Product>> getAllProduct(Product product) {
        List<Product> list = repo.getAllProduct();
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(list);
        return apiRespone;
    }
    @PostMapping(path = "/add")
    public ApiRespone<Product> addProduct(
        @RequestBody ProductRequest product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.addProduct(product));
        return apiRespone;
    }
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteProductByID(
        @PathVariable(name = "id") Long ID) {
        ApiRespone apiRespone = new ApiRespone<>();
        repo.deleteProduct(ID);
        apiRespone.setData(true);
        return apiRespone;
    }
    @PutMapping(path = "/update/{id}")
    public ApiRespone<String> changeProduct(
        @PathVariable(name = "id", required = true) Long ID,
            @RequestBody ProductRequest product) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.updateProduct(ID, product));
        return apiRespone;
    }
    @GetMapping(path = "/getbycategory/{name}")
    public ApiRespone<List<Product>> getProductByCategory(
        @PathVariable(name = "name") String name) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByCategory(name));
        return apiRespone;
    }
    @GetMapping(path = "/getbyname/{name}")
    public ApiRespone<List<Product>> getProductByName(
        @PathVariable(name = "name") String name) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByName(name));
        return apiRespone;
    }
    @GetMapping(path = "/getbybrand/{brand}")
    public ApiRespone<List<Product>> getProductByBrand(
        @PathVariable(name = "brand") String brand) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByBrand(brand));
        return apiRespone;
    }
    @GetMapping(path = "/getbybrandandcategory/{brand}/{category}")
    public ApiRespone<List<Product>> getProductByBrandAndCategory(
        @PathVariable(name = "brand") String brand,
    @PathVariable(name = "category") String category) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByBrandAndCategory(brand, category));
        return apiRespone;
    }
    @GetMapping(path = "/getbybrandandinventory/{brand}/{inventory}")
    public ApiRespone<List<Product>> getProductByBrandAndInventiontory(
        @PathVariable(name = "brand") String brand,
    @PathVariable(name = "inventory") Long inventory) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByBrand(brand));
        return apiRespone;
    }
    @GetMapping(path = "/getbybrandandname/{brand}/{name}")
    public ApiRespone<List<Product>> getProductByBrandAndName(
        @PathVariable(name = "brand") String brand,
    @PathVariable(name = "name") String name) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.getProductByBrand(brand));
        return apiRespone;
    }
    @GetMapping(path = "/countbybrandandname/{brand}/{name}")
    public ApiRespone<Long> countProductByBrandAndName(
        @PathVariable(name = "brand") String brand,
        @PathVariable(name = "name") String name) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(repo.countProductByBrandAndName(brand, name));
        return apiRespone;
    }
}

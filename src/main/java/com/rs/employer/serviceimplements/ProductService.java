package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CategoryRepository;
import com.rs.employer.dao.ProductRepository;
import com.rs.employer.dto.Request.Product.ProductRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ProductMapper;
import com.rs.employer.model.Category;
import com.rs.employer.model.Product;
import com.rs.employer.service.IProductService;

import lombok.extern.slf4j.Slf4j;

// Service implement for product

@Slf4j
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    Instant now = Instant.now();
    @Autowired
    ProductMapper mapper;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public Product addProduct(ProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category category1 = new Category(request.getCategory().getName());
                    return categoryRepository.save(category1);
                });
        Product product = mapper.toProduct(request);
        product.setCategory(category);
        product.setCreateAt(now);
        product.setUpdateAt(now);
        return productRepository.save(product);
    }

    @PreAuthorize("hasAuthority('SCOPE_DELETE_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public void deleteProduct(Long ID) {
        productRepository.findById(ID).ifPresentOrElse(product -> productRepository.delete(product), () -> {
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
        });
    }

    @PreAuthorize("hasAuthority('SCOPE_UPDATE_PRODUCT') or hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public Product updateProduct(Long ID, ProductRequest request) {
        if (!productRepository.existsById(ID)) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        } else {
            Product product = mapper.toProduct(request);
            product.setUpdateAt(now);
            return productRepository.save(product);
        }

    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public Optional<Product> getProduct(Long ID) {
        if (productRepository.existsById(ID))
            return productRepository.findById(ID);
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByCategory(String name) {
        if (productRepository.existsByCategoryName(name)) {
            return productRepository.findByCategoryName(name);
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBrand(String brain) {
        List<Product> products = productRepository.findByBrand(brain);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);

    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByName(String name) {
        List<Product> products = productRepository.findByName(name);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);

    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBrandAndCategory(String brand, String categoryname) {
        List<Product> products = productRepository.findByBrandAndCategoryName(brand,categoryname);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBrandAndInventory(String brand, Long inventory) {
        List<Product> products = productRepository.findByBrandAndInventory(brand,inventory);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBraindAndName(String brand, String name) {
        List<Product> products = productRepository.findByBrandAndName(brand,name);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

}

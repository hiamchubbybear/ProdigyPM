package com.rs.employer.service.warehouse;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.rs.employer.model.others.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.warehouse.CategoryRepository;
import com.rs.employer.dao.warehouse.ProductRepository;
import com.rs.employer.dto.Request.Product.ProductRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.ProductMapper;
import com.rs.employer.model.warehouse.Product;

import lombok.extern.slf4j.Slf4j;

// Service implement for product

@Slf4j
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    Instant now = Instant.now();
    ProductMapper mapper;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public Product addProduct(ProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByCategoryName(request.getCategory().getCategoryName()))
                .orElseGet(() -> {
                    Category category1 = new Category();
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
            throw new AppException(ErrorCode.PRODUCT_ALREADY_EXISTS);
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
        if (productRepository.existsByCategoryCategoryName(name)) {
            return productRepository.findByCategoryCategoryName(name);
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
        List<Product> products = productRepository.findByProductName(name);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);

    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBrandAndCategory(String brand, String categoryname) {
        List<Product> products = productRepository.findByBrandAndCategory_CategoryName(brand,categoryname);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBrandAndInventory(String brand, Long inventory) {
        List<Product> products = productRepository.findByBrandAndInventories(brand,inventory);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> getProductByBraindAndName(String brand, String name) {
        List<Product> products = productRepository.findByBrandAndProductName(brand,name);
        if (!products.isEmpty())
            return products;
        else
            throw new AppException(ErrorCode.PRODUCT_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ADD_PRODUCT')or hasAuthority('SCOPE_PERMIT_ALL')")
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndProductName(brand, name);
    }

}

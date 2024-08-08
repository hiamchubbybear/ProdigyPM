package com.rs.employer.mapper;

import org.mapstruct.Mapper;

import com.rs.employer.dto.Request.ProductRequest;
import com.rs.employer.dto.Respone.ProductRespone;
import com.rs.employer.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductRespone toProductRespone(ProductRequest request);

    Product toProduct(ProductRequest request);

}

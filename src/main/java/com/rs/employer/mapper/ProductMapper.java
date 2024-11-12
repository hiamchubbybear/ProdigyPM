package com.rs.employer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.dto.Request.Product.ProductRequest;
import com.rs.employer.dto.Respone.ProductRespone;
import com.rs.employer.model.warehouse.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductRespone toProductRespone(ProductRequest request);
    @Mapping(ignore = true , target = "images" )
    @Mapping(ignore = true , target = "status" )
    @Mapping(ignore= true ,target = "category")
    Product toProduct(ProductRequest request);

}

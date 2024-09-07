package com.rs.employer.serviceimplements;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.model.Image;
import com.rs.employer.model.ImageRequest;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "image", ignore = true)
    Image toImage(ImageRequest request);
}

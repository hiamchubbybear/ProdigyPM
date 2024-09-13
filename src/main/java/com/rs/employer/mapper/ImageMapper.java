package com.rs.employer.mapper;

import com.rs.employer.dto.Request.ImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.model.Image;
import com.rs.employer.model.ImageRequest;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toImageFromDTO(ImageDTO imageDTO);
}

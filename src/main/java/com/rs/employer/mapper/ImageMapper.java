package com.rs.employer.mapper;

import com.rs.employer.dto.Request.ImageDTO;
import org.mapstruct.Mapper;

import com.rs.employer.model.others.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image toImageFromDTO(ImageDTO imageDTO);
}

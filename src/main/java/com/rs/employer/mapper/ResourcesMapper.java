package com.rs.employer.mapper;

import org.mapstruct.Mapper;

import com.rs.employer.dto.Request.Resources.ResourcesRequest;
import com.rs.employer.dto.Respone.ResourcesRespone;
import com.rs.employer.model.warehouse.Resources;

@Mapper(componentModel = "spring")
public interface ResourcesMapper {
    ResourcesRespone toResourcesRespone(ResourcesRequest request);

    Resources toResources(ResourcesRequest request);

}

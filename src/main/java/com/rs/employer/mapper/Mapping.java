package com.rs.employer.mapper;

import org.mapstruct.Mapper;

import com.rs.employer.model.Customer;

@Mapper(componentModel = "spring")
public interface Mapping {
    Customer customerMapper(Customer customerMapper);
}

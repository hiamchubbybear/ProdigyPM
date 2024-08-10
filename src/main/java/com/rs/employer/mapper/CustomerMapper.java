package com.rs.employer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.dto.Request.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;
import com.rs.employer.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "role", ignore = true)
    CustomerRespone toCustomerRespone(CustomerRequest request);

    @Mapping(target = "role", ignore = true)
    Customer toCustomer(CustomerRequest request);

    CustomerRespone toCustomerRespone(Customer request);
}

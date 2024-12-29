package com.rs.employer.mapper;

import com.rs.employer.dto.Response.CustomerInfoDTO;
import com.rs.employer.dto.Response.CustomerResponse;
import com.rs.employer.model.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.dto.Request.User.CustomerRequest;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "role", ignore = true)
    CustomerResponse toCustomerRespone(CustomerRequest request);

    @Mapping(target = "role", ignore = true)
    Customer toCustomer(CustomerRequest request);

    @Mapping(target = "role", ignore = true)
    CustomerResponse toCustomerRespone(Customer request);

    CustomerInfoDTO toCustomerInfoDTO(Customer customer);
    Customer toCustomer(CustomerInfoDTO customerInfoDTO);
}

package com.rs.employer.mapper;

import com.rs.employer.dto.Respone.CustomerInfoDTO;
import com.rs.employer.model.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "role", ignore = true)
    CustomerRespone toCustomerRespone(CustomerRequest request);

    @Mapping(target = "roles", ignore = true)
    Customer toCustomer(CustomerRequest request);

    @Mapping(target = "role", ignore = true)
    CustomerRespone toCustomerRespone(Customer request);

    CustomerInfoDTO toCustomerInfoDTO(Customer customer);
    Customer toCustomer(CustomerInfoDTO customerInfoDTO);
}

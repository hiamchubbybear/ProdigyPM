package com.rs.employer.mapper;

import org.mapstruct.Mapper;

import com.rs.employer.dto.Request.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;
import com.rs.employer.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerRespone toCustomerRespone(CustomerRequest request);

    Customer toCustomer(CustomerRequest request);

    CustomerRespone toCustomerRespone(Customer request);
}

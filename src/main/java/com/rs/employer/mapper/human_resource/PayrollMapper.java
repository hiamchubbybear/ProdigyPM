package com.rs.employer.mapper.human_resource;

import com.rs.employer.dto.Request.human_resource.PayrollRequest;
import com.rs.employer.model.human_resource.Payroll;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PayrollMapper {
    @Mapping(target = "employeeName" , ignore = true)
    @Mapping(target = "customerId" , ignore = true)
    Payroll payrollFromPayRollRequest(PayrollRequest payrollRequest);
}

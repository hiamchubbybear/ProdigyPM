package com.rs.employer.mapper.human_resource;

import com.rs.employer.dto.Request.EmployeeRequest;
import com.rs.employer.dto.respone.EmployeeResponse;
import com.rs.employer.model.human_resource.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "payrolls" , ignore = true)
    @Mapping(target = "department" , ignore = true)
     Employee toEmployeeRequest(EmployeeRequest employee);
//    @Mapping(target = "payrolls" , ignore = true)
//    @Mapping(target = "department" , ignore = true)
//    EmployeeResponse toEmployeeResponse(EmployeeRequest employee);

}

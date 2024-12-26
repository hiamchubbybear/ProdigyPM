
package com.rs.employer.mapper;

import com.rs.employer.dto.OrderDTO;
import com.rs.employer.model.sale.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(ignore = true , target = "createBy")
    Order toEntity(OrderDTO orderDTO);
    @Mapping(ignore = true , target = "createBy")
    OrderDTO toDTO(Order order);
}
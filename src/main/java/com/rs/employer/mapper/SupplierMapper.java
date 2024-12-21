package com.rs.employer.mapper;

import com.rs.employer.dto.SupplierDTO;
import com.rs.employer.model.purchase.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    SupplierDTO toDTO(Supplier supplier);

    Supplier toEntity(SupplierDTO supplierDTO);
}
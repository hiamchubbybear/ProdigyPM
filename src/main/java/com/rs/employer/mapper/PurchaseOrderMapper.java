package com.rs.employer.mapper;


import com.rs.employer.dto.PurchaseOrderDTO;
import com.rs.employer.model.purchase.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.*;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {

    @Mapping(target = "supplierId" , ignore = true) // Ánh xạ Supplier entity sang supplierId trong DTO
    @Mapping(target = "createByCustomerId" , ignore = true) // Ánh xạ Customer entity sang createByCustomerId
    @Mapping(target = "purchaseOrderId", ignore = true)
        // Bỏ qua field không cần ánh xạ
    PurchaseOrderDTO toDTO(PurchaseOrder purchaseOrder);

    @Mapping(target = "supplier" , ignore = true) // Ánh xạ ngược supplierId từ DTO sang Supplier entity
    @Mapping(target = "createBy" , ignore = true) // Ánh xạ ngược createByCustomerId từ DTO sang Customer entity
    @Mapping(target = "totalAmount", ignore = true)
        // Bỏ qua trường không ánh xạ
    PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);
}
package com.rs.employer.dto.Interface;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface ProductDto {
    Long getProductId();

    String getProductName();

    String getProductType();

    Long getProductSize();

    Long getProductWeight();

    String getProductWeightUnit();

    String getProductSizeUnit();

    Date getProductExp();

    Instant getCreateAt();

    Instant getUpdateAt();

    String getProductSubtitle();

    String getProductUnit();
}

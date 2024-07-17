package com.rs.employer.dto;

import java.time.Instant;
import java.util.Date;

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

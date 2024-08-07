package com.rs.employer.dto.Respone;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public interface ProductRespone {
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

package com.rs.employer.dto;

import java.time.Instant;
import java.util.Date;

import lombok.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
@Getter
@Setter
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

package com.retail.ordering.system.orderservice.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String productCode;
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean productLowAvailability;

}

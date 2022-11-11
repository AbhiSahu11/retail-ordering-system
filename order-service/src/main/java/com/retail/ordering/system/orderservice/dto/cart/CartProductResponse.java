package com.retail.ordering.system.orderservice.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponse {
    private String productCode;
    private String productName;
    private String productDescription;
    private double productPrice;
    private boolean productLowAvailability;
}

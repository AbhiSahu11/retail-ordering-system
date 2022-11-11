package com.retail.ordering.system.orderservice.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckoutItemDto {
    private String productName;
    private int  quantity;
    private double price;
    private long productId;
    private int userId;
}

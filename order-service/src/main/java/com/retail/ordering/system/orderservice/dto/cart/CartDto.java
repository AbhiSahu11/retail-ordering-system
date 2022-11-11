package com.retail.ordering.system.orderservice.dto.cart;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalCost;
    public List<CartItemDto> getcartItems() {
        return cartItems;
    }
}

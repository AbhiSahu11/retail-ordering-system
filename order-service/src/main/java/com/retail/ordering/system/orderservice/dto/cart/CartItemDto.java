package com.retail.ordering.system.orderservice.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Integer price;

    public CartItemDto(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}

package com.retail.ordering.system.orderservice.dto.cart;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddToCartDto {
    private Integer id;
    private @NotNull String productCode;
    private @NotNull int quantity;
    private @NotNull String userId;
}

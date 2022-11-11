package com.retail.ordering.system.orderservice.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDto {
    private @NotNull double price;
    private @NotNull int quantity;
    private @NotNull int orderId;
    private @NotNull int productId;
}

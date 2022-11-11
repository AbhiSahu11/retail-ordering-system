package com.retail.ordering.system.orderservice.dto.order;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private Integer id;
    private @NotNull Integer userId;
}

package com.retail.ordering.system.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class OrderResponse {
	private final boolean success;
	private final String message;
}

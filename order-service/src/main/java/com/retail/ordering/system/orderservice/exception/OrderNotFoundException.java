package com.retail.ordering.system.orderservice.exception;

public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}

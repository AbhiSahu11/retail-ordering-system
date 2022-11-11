package com.retail.ordering.system.orderservice.exception;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String msg) {
        super(msg);
    }
}

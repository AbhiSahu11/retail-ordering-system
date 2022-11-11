package com.retail.ordering.system.orderservice.exception;

public class CustomException extends IllegalArgumentException {
    public CustomException(String msg) {
        super(msg);
    }
}

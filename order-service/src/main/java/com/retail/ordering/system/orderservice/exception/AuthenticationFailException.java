package com.retail.ordering.system.orderservice.exception;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}

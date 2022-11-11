package com.retail.ordering.system.orderservice.exception;

public class UpdateFailException extends IllegalArgumentException {
    public UpdateFailException(String msg) {
        super(msg);
    }
}

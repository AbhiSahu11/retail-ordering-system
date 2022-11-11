package com.retail.ordering.system.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException() {
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String s, String productCode) {
        super(productCode);
    }
}

package com.enums;

public class OrderValidationException extends RuntimeException {

    public OrderValidationException(String message) {
        super("Order validation: " + message);
    }
}

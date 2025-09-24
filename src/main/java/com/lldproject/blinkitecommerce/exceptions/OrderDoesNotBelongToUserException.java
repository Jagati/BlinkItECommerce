package com.lldproject.blinkitecommerce.exceptions;

public class OrderDoesNotBelongToUserException extends Exception {
    public OrderDoesNotBelongToUserException(String message) {
        super(message);
    }
}

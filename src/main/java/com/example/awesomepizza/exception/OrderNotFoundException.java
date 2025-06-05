package com.example.awesomepizza.exception;

public class OrderNotFoundException extends RuntimeException {
    
    public OrderNotFoundException(String orderId) {
        super("order with ID " + orderId + " not found.");
    }
}
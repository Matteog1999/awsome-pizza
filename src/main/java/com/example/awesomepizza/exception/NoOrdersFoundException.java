package com.example.awesomepizza.exception;

public class NoOrdersFoundException extends RuntimeException {

    public NoOrdersFoundException() {
        super("no orders found");
    }
}
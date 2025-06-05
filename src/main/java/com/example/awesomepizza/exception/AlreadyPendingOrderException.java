package com.example.awesomepizza.exception;

public class AlreadyPendingOrderException extends RuntimeException {

    public AlreadyPendingOrderException() {
        super("orderd already pending");
    }
}
package com.example.outsourcing.global.exception;

public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException(String message) {
        super(message);
    }
}

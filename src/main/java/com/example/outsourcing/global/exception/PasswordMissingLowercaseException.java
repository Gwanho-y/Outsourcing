package com.example.outsourcing.global.exception;

//소문자가 1개 이상 포함되어야함
public class PasswordMissingLowercaseException extends RuntimeException {
    public PasswordMissingLowercaseException() {
    }
}
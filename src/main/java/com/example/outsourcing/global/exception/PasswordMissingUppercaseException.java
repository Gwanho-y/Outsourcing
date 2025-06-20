package com.example.outsourcing.global.exception;

//대문자가 1개 이상 포함되어야함
public class PasswordMissingUppercaseException extends RuntimeException {
    public PasswordMissingUppercaseException() {
    }
}
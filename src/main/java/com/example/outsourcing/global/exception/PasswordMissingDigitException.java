package com.example.outsourcing.global.exception;

//숫자가 1개 이상 포함되어야함
public class PasswordMissingDigitException extends RuntimeException {
    public PasswordMissingDigitException() {
    }
}
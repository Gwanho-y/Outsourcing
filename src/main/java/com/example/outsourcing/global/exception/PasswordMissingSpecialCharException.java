package com.example.outsourcing.global.exception;

//특수문자가 1개 이상 포함되어야함
public class PasswordMissingSpecialCharException extends RuntimeException {
    public PasswordMissingSpecialCharException() {
    }
}
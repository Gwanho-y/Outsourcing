package com.example.outsourcing.global.exception;

//비밀번호가 너무 짧음
public class PasswordTooShortException extends RuntimeException {
    public PasswordTooShortException() {
    }
}

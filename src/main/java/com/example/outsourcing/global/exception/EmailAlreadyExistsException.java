package com.example.outsourcing.global.exception;

//이미 사용중인 이메일임
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
    }
}
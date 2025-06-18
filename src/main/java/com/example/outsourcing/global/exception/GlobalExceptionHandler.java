package com.example.outsourcing.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordTooShortException.class)
    public ResponseEntity<String> PasswordTooShortException() {
        ResponseEntity<String> response = new ResponseEntity<>("비밀번호는 최소 8자 이상이어야 합니다.", HttpStatus.BAD_REQUEST);
        return response;
    }
    @ExceptionHandler(PasswordMissingUppercaseException.class)
    public ResponseEntity<String> PasswordMissingUppercaseException() {
        ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 대문자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
        return response;
    }
    @ExceptionHandler(PasswordMissingLowercaseException.class)
    public ResponseEntity<String> PasswordMissingLowercaseException() {
        ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 소문자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
        return response;
    }
    @ExceptionHandler(PasswordMissingDigitException.class)
    public ResponseEntity<String> PasswordMissingDigitException() {
        ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 숫자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
        return response;
    }
    @ExceptionHandler(PasswordMissingSpecialCharException.class)
    public ResponseEntity<String> PasswordMissingSpecialCharException() {
        ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 특수문자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
        return response;
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> EmailAlreadyExistsException() {
        ResponseEntity<String> response = new ResponseEntity<>("이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT);
        return response;
    }
}

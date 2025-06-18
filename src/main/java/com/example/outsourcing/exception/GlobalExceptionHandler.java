package com.example.outsourcing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LogNotFoundException.class)
    public ResponseEntity<String> handleInvalidRequestException(LogNotFoundException ex) {
        return new ResponseEntity<>("잘못된 요청입니다: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException() {
        return new ResponseEntity<>("예외처리구조", HttpStatus.OK);
    }
    //오류 응답 DTO도 만듫기
}

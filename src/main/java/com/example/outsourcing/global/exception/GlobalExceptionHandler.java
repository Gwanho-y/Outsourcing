package com.example.outsourcing.global.exception;

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
    // Task 관련 예외 처리
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException taskNotFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("해당 태스크를 찾을 수 없습니다: " + taskNotFoundException.getMessage());
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException() {
        return new ResponseEntity<>("예외처리구조", HttpStatus.OK);
    }
    //오류 응답 DTO도 만듫기
}

    // 이후 다른 도메인의 예외들도 여기에 추가
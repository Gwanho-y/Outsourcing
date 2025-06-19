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
        public ResponseEntity<String> PasswordMissingLowercaseException () {
            ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 소문자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
            return response;
        }
        //오류 응답 DTO도 만듫기
        @ExceptionHandler(PasswordMissingDigitException.class)
        public ResponseEntity<String> PasswordMissingDigitException () {
            ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 숫자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
            return response;
        }
        @ExceptionHandler(PasswordMissingSpecialCharException.class)
        public ResponseEntity<String> PasswordMissingSpecialCharException () {
            ResponseEntity<String> response = new ResponseEntity<>("비밀번호에는 특수문자가 최소 1개 포함되어야 합니다.", HttpStatus.BAD_REQUEST);
            return response;
        }
        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<String> EmailAlreadyExistsException () {
            ResponseEntity<String> response = new ResponseEntity<>("이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT);
            return response;
        }
    }


    // 이후 다른 도메인의 예외들도 여기에 추가
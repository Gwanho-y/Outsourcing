package com.example.outsourcing.global.exception;

public class Exceptions {
    //비밀번호가 너무 짧음
    public static class PasswordTooShortException extends RuntimeException {
        public PasswordTooShortException() {
        }
    }

    //대문자가 1개 이상 포함되어야함
    public static class PasswordMissingUppercaseException extends RuntimeException {
        public PasswordMissingUppercaseException() {
        }
    }

    //소문자가 1개 이상 포함되어야함
    public static class PasswordMissingLowercaseException extends RuntimeException {
        public PasswordMissingLowercaseException() {
        }
    }

    //숫자가 1개 이상 포함되어야함
    public static class PasswordMissingDigitException extends RuntimeException {
        public PasswordMissingDigitException() {
        }
    }

    //특수문자가 1개 이상 포함되어야함
    public static class PasswordMissingSpecialCharException extends RuntimeException {
        public PasswordMissingSpecialCharException() {
        }
    }

    //이미 사용중인 이메일임
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException() {
        }
    }
}

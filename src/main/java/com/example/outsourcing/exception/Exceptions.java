package com.example.outsourcing.exception;

public class Exceptions {
    //비밀번호가 너무 짧음
    public static class PasswordTooShortException extends RuntimeException {
        public PasswordTooShortException() {
            super("비밀번호는 최소 8자 이상이어야 합니다.");
        }
    }

    //대문자가 1개 이상 포함되어야함
    public static class PasswordMissingUppercaseException extends RuntimeException {
        public PasswordMissingUppercaseException() {
            super("비밀번호에는 대문자가 최소 1개 포함되어야 합니다.");
        }
    }

    //소문자가 1개 이상 포함되어야함
    public static class PasswordMissingLowercaseException extends RuntimeException {
        public PasswordMissingLowercaseException() {
            super("비밀번호에는 소문자가 최소 1개 포함되어야 합니다.");
        }
    }

    //숫자가 1개 이상 포함되어야함
    public static class PasswordMissingDigitException extends RuntimeException {
        public PasswordMissingDigitException() {
            super("비밀번호에는 숫자가 최소 1개 포함되어야 합니다.");
        }
    }

    //특수문자가 1개 이상 포함되어야함
    public static class PasswordMissingSpecialCharException extends RuntimeException {
        public PasswordMissingSpecialCharException() {
            super("비밀번호에는 특수문자가 최소 1개 포함되어야 합니다.");
        }
    }

    //이미 사용중인 이메일임
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException() {
            super("이미 사용 중인 이메일입니다.");
        }
    }
}

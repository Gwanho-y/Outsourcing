package com.example.outsourcing.global.exception;

public class Exceptions {
    // 댓글을 찾을 수 없음
    public static class CommentNotFoundException extends RuntimeException {
        public CommentNotFoundException() {
            super("댓글이 존재하지 않습니다.");
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

    // 본인이 작성한 댓글이 아님
    public static class CommentUserMismatchException extends RuntimeException {
        public CommentUserMismatchException() {
            super("해당 유저의 댓글이 아닙니다.");
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

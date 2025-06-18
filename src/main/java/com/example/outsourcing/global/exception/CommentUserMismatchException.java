package com.example.outsourcing.global.exception;

public class CommentUserMismatchException extends RuntimeException {
    public CommentUserMismatchException() {super("해당 유저의 댓글이 아닙니다.");}
}

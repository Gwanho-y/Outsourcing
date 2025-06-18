package com.example.outsourcing.exception;

public class Exceptions {
    // 댓글을 찾을 수 없음
    public static class CommentNotFoundException extends RuntimeException {
        public CommentNotFoundException() {
            super("댓글이 존재하지 않습니다.");
        }
    }

    // 본인이 작성한 댓글이 아님
    public static class CommentUserMismatchException extends RuntimeException {
        public CommentUserMismatchException() {
            super("해당 유저의 댓글이 아닙니다.");
        }
    }

}

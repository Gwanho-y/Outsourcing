package com.example.outsourcing.dto.comment;

import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {
    private final Long commentId;
    private final Long taskId;
    private final Long userId;
    private final String content;

    public CommentUpdateRequestDto(Long commentId, Long taskId, Long userId, String content) {
        this.commentId = commentId;
        this.taskId = taskId;
        this.userId = userId;
        this.content = content;
    }
}

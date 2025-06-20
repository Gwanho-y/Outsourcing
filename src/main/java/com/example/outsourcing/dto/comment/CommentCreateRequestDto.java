package com.example.outsourcing.dto.comment;

import lombok.Getter;

@Getter
public class CommentCreateRequestDto {
    private final Long taskId;
    private final Long userId;
    private final String content;

    public CommentCreateRequestDto(Long taskId, Long userId, String content) {
        this.taskId = taskId;
        this.userId = userId;
        this.content = content;
    }
}

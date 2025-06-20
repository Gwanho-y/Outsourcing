package com.example.outsourcing.dto.comment;

import com.example.outsourcing.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {
    private final Long commentId;
    private final Long taskId;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentCreateResponseDto(CommentEntity commentEntity) {
        this.commentId = commentEntity.getCommentId();
        this.taskId = commentEntity.getTaskId().getTaskId();
        this.userId = commentEntity.getUser().getId();
        this.content = commentEntity.getContent();
        this.createdAt = commentEntity.getCreatedAt();
        this.updatedAt = commentEntity.getUpdatedAt();
    }
}

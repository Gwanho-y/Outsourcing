package com.example.outsourcing.dto.comment;

import com.example.outsourcing.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class CommentUpdateResponsetDto {
    private final Long commentId;
    private final Long taskId;
    private final Long userId;
    private final String content;
    private final LocalDateTime updatedAt;

    public CommentUpdateResponsetDto(CommentEntity commentEntity) {
        this.commentId = commentEntity.getCommentId();
        this.taskId = commentEntity.getTaskId().getTaskId();
        this.userId = commentEntity.getUser().getId();
        this.content = commentEntity.getContent();
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}

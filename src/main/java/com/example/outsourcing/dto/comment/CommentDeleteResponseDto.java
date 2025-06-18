package com.example.outsourcing.dto.comment;

import com.example.outsourcing.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDeleteResponseDto {
    private final Long commentId;
    private final boolean isDeleted;
    private final LocalDateTime deletedAt;

    public CommentDeleteResponseDto(CommentEntity commentEntity) {
        this.commentId = commentEntity.getCommentId();
        this.isDeleted = commentEntity.isDeleted();
        this.deletedAt = commentEntity.getDeletedAt();
    }
}

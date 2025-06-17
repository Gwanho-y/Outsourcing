package com.example.outsourcing.entity;

import lombok.Getter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(optional=false)
    private TaskEntity taskId;

    @ManyToOne(optional = false)
    private UserEntity userId;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist void onCreate(){
        createdAt=LocalDateTime.now();
    }
    @PreUpdate void onUpdate(){
        updatedAt=LocalDateTime.now();
    }

    public void setTaskId(TaskEntity taskId) {
        this.taskId = taskId;
    }

    public void setUserId(UserEntity user) {
        this.userId = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDeletedAt() {
        this.deletedAt = LocalDateTime.now();
    }
}
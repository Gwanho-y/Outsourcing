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
    private UserEntity user;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
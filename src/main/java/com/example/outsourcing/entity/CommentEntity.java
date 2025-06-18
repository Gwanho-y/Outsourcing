package com.example.outsourcing.entity;

import lombok.Getter;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

    @Column
    private boolean isDeleted;

    @Column
    private LocalDateTime deletedAt;

    protected CommentEntity() {

    }

    public CommentEntity(TaskEntity taskId, UserEntity userId, String content) {
        this.taskId = taskId;
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
        this.isDeleted = false;
    }

    @PrePersist void onCreate(){
        createdAt=LocalDateTime.now();
    }

    public void updateComment(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    public void delete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate void onUpdate(){
        updatedAt=LocalDateTime.now();
    }
}
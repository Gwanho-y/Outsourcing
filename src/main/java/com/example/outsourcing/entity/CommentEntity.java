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
    @JoinColumn(name = "task_id")
    private TaskEntity taskId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @Column
    private boolean deleted;

    @Column
    private LocalDateTime deletedAt;

    protected CommentEntity() {

    }

    public CommentEntity(TaskEntity taskId, UserEntity userId, String content) {
        this.taskId = taskId;
        this.user = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
        this.deleted = false;
    }

    @PrePersist void onCreate(){
        createdAt=LocalDateTime.now();
    }

    public void updateComment(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate void onUpdate(){
        updatedAt=LocalDateTime.now();
    }
}
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

    @PrePersist void onCreate(){
        createdAt=LocalDateTime.now();
    }
    @PreUpdate void onUpdate(){
        updatedAt=LocalDateTime.now();
    }
}
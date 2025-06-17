package com.example.outsourcing.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String taskContent;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 기본 생성자
    protected TaskEntity() {

    }

    // 생성자(Setter 어노테이션을 안쓰기때문)
    public TaskEntity(UserEntity user, String title, String taskContent, String status) {
        this.user = user;
        this.title = title;
        this.taskContent = taskContent;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateTask(String title, String taskContent, String status) {
        this.title = title;
        this.taskContent = taskContent;
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
}







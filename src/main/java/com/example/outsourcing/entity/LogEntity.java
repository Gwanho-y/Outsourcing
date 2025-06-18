package com.example.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Getter

public class LogEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "name", length = 5, nullable = false)
    private String name;

    @Column(name = "act_type", length = 25, nullable = false)
    private String actType;

    @Column(name = "log_content", length = 255, nullable = false)
    private String logContent;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    protected LogEntity() {
        //JPA 생성자 외부 호출 X
    }

    //user 객체에서 id랑 이름 뽑기
    private LogEntity(UserEntity user, String actType, String logContent) {
        this.user = user;
        this.actType = actType;
        this.logContent = logContent;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.user != null) {
            this.name = this.user.getName();
        }
    }

    public static LogEntity logFromTaskCreate(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromTaskUpdate(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromTaskDelete(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromCommentCreate(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromCommentUpdate(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromCommentDelete(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromLogIn(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }

    public static LogEntity logFromLogOut(UserEntity user, String actType, String logContent) {
        LogEntity logEntity = new LogEntity(user, actType, logContent);
        return logEntity;
    }
}
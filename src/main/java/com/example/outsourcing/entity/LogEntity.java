package com.example.outsourcing.entity;

import com.mysql.cj.log.Log;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Table(name = "/api/logs")
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
/*
task
태스크 생성
태스크 수정
태스크 삭제
태스크 상태 변경
comment
댓글 생성
댓글 수정
댓글 삭제
user
로그인
로그아웃

테이블 형태
로그 id   / 유저 id  /   이름    / 제목 / 내용 / 시간
자동 생성 / user객체 / user 객체 / 수동 / 수동 /  자동

1. 태스크 생성
LogEntity log = LogEntity.logFromTaskCreate(user, "작업 생성", "작업이 생성되었습니다")
logRepository.save(log);

2. 태스크 수정
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 수정");
        log.setLogContent("작업이 수정되었습니다.");
        logRepository.save(log);

3.태스크 삭제
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 삭제");
        log.setLogContent("작업이 삭제었습니다.");
        logRepository.save(log);

4.태스크 상태 변경
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 완료");
        log.setLogContent("작업이 완료되었습니다.");
        logRepository.save(log);

1.댓글 생성
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 생성");
        log.setLogContent("작업이 생성되었습니다.");
        logRepository.save(log);

2.댓글 수정
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 생성");
        log.setLogContent("작업이 생성되었습니다.");
        logRepository.save(log);

3.댓글 삭제
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 생성");
        log.setLogContent("작업이 생성되었습니다.");
        logRepository.save(log);

1.로그인
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 생성");
        log.setLogContent("작업이 생성되었습니다.");
        logRepository.save(log);

2.로그아웃
LogEntity log = new LogEntity();
        log.setUser(user);
        log.setActType("작업 생성");
        log.setLogContent("작업이 생성되었습니다.");
        logRepository.save(log);





 */
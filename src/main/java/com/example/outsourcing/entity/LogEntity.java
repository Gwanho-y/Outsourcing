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
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "name", length = 5, nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "log_content", length = 255, nullable = false)
    private String logContent;

    @Column(name = "act_type", length = 25, nullable = false)
    private String actType;

    //
    public LogEntity(Long logId, UserEntity name, String actType, String logContent ) {
        this.logId = logId;
        this.actType = actType;
        this.logContent = logContent;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.task != null) {
            this.title = this.task.getTitle();
        }

        if (this.user != null) {
            this.name = this.user.getName();
        }
    }
}
/*
태스크 생성
태스크 수정
태스크 삭제
태스크 상태 변경

댓글 생성
댓글 수정
댓글 삭제

로그인
로그아웃

로그고유 id / 사용자 / 활동 유형 / 활동 내용 / 시간
logId / name / actType / logContent / createdAt

1. 태스크 생성
? / 이영재 / 작업 생성 / 작업이 생성되었습니다. / 00-00-00

2. 태스크 수정
? / 이영재 / 작업 수정 / 작업이 수정되었습니다. / 00-00-00

3.태스크 삭제
? / 이영재 / 작업 삭제 / 작업이 삭제되었습니다. / 00-00-00

4.태스크 상태 변경
? / 이영재 / 작업 시작 / 작업이 시작되었습니다. / 00-00-00
? / 이영재 / 작업 완료 / 작업이 완료되었습니다. / 00-00-00

1.댓글 생성
? / 이영재 / 댓글 생성 / 댓글이 작성되었습니다. / 00-00-00

2.댓글 수정
? / 이영재 / 댓글 수정 / 댓글이 수정되었습니다. / 00-00-00

3.댓글 삭제
? / 이영재 / 댓글 삭제 / 댓글이 삭제되었습니다. / 00-00-00

1.로그인
? / 이영재 / 로그인 / 로그인 / 00-00-00

2.로그아웃
? / 이영재 / 로그아웃 / 로그아웃 / 00-00-00





 */
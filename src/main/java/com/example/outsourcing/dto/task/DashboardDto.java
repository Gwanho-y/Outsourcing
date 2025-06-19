package com.example.outsourcing.dto.task;

import com.example.outsourcing.entity.TaskStatus;
import lombok.Getter;

@Getter
public class DashboardDto {
    //대시보드에 쓸것들
    private TaskStatus status;
    private long count;
    private double percentage;
    //필드 초기화
    public DashboardDto(TaskStatus status, long count, double percentage) {
        this.status = status;
        this.count = count;
        this.percentage = percentage;
    }
}

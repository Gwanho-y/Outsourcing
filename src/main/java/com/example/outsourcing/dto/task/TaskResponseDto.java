package com.example.outsourcing.dto.task;

import com.example.outsourcing.entity.TaskEntity;
import lombok.Getter;

@Getter
public class TaskResponseDto {

    private String title;
    private String taskContent;
    private String status;
    private Long taskId;
    private String createdAt;

    public TaskResponseDto(TaskEntity task) {
        this.title = task.getTitle();
        this.taskContent = task.getTaskContent();
        this.status = task.getStatus();
        this.taskId = task.getTaskId();
        this.createdAt = task.getCreatedAt().toString(); // 타입 String으로 맞춰줘야함
    }
}
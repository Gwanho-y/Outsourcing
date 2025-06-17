package com.example.outsourcing.dto.task;

import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.TaskStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto {
    private final Long taskId;
    private final String title;
    private final String taskContent;
    private final TaskStatus taskStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public TaskResponseDto(TaskEntity task) {
        this.taskId = task.getTaskId();
        this.title = task.getTitle();
        this.taskContent = task.getTaskContent();
        this.taskStatus = task.getTaskStatus();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }
}
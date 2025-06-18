package com.example.outsourcing.dto.task;

import com.example.outsourcing.entity.TaskStatus;
import lombok.Getter;

@Getter
public class TaskStatusUpdateRequestDto {
    private TaskStatus taskStatus;

    public TaskStatusUpdateRequestDto(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
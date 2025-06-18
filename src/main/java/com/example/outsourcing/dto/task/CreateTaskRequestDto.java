package com.example.outsourcing.dto.task;

import com.example.outsourcing.entity.TaskStatus;
import lombok.Getter;

@Getter
public class CreateTaskRequestDto {
    private String title;
    private String taskContent;
    private TaskStatus taskStatus;

    public CreateTaskRequestDto(String title, String taskContent, TaskStatus taskStatus) {
        this.title = title;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
    }
}
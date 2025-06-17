package com.example.outsourcing.dto.task;

import com.example.outsourcing.entity.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TaskRequestDto {

    private String title;
    private String taskContent;
    private TaskStatus taskStatus;

    public TaskRequestDto() {

    }

    public TaskRequestDto(String title, String taskContent, TaskStatus taskStatus) {
        this.title = title;
        this.taskContent = taskContent;
        this.taskStatus = taskStatus;
    }

}

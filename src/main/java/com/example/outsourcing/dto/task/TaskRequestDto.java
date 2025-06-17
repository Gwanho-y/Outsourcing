package com.example.outsourcing.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TaskRequestDto {

    private String title;
    private String taskContent;
    private String status;

    public TaskRequestDto() {

    }

    public TaskRequestDto(String title, String taskContent, String status) {
        this.title = title;
        this.taskContent = taskContent;
        this.status = status;
    }

}
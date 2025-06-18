package com.example.outsourcing.dto.task;

import lombok.Getter;

@Getter
public class UpdateTaskRequestDto {
    private String title;
    private String taskContent;

    public UpdateTaskRequestDto(String title, String taskContent) {
        this.title = title;
        this.taskContent = taskContent;
    }
}

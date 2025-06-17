package com.example.outsourcing.dto.task;

import lombok.Getter;

@Getter
public class TaskStatusUpdateRequestDto {

    private String status;

    public TaskStatusUpdateRequestDto() {

    }

    public TaskStatusUpdateRequestDto(String status) {
        this.status = status;
    }
}

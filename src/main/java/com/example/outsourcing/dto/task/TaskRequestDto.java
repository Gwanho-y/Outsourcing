package com.example.outsourcing.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskRequestDto {

    private String title;
    private String taskContent;
    private String status;

}
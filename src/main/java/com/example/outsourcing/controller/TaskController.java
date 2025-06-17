package com.example.outsourcing.controller;

import com.example.outsourcing.dto.task.TaskRequestDto;
import com.example.outsourcing.dto.task.TaskResponseDto;
import com.example.outsourcing.dto.task.TaskStatusUpdateRequestDto;
import com.example.outsourcing.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 태스크 생성, 인증된 유저만
    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(
            @RequestBody TaskRequestDto taskRequestDto,
            @RequestParam("userId") Long userId //로그인 유저 ID
    ) {
        TaskResponseDto responseDto = taskService.createTask(taskRequestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED); // 201
    }

    // 태스크 수정
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @RequestBody TaskRequestDto taskRequestDto,
            @PathVariable Long taskId
    ) {
        TaskResponseDto responseDto = taskService.updateTask(taskRequestDto, taskId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK); // 200
    }

    // 태스크 상태 변경
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @RequestBody TaskStatusUpdateRequestDto requestDto,
            @PathVariable Long taskId
    ) {
        TaskResponseDto responseDto = taskService.updateTaskStatus(requestDto, taskId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 태스크 삭제
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId
    ) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

}

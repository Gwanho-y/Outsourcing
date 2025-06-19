package com.example.outsourcing.controller;

import com.example.outsourcing.dto.task.*;
import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestBody CreateTaskRequestDto createTaskRequestDto
    ) {
        TaskResponseDto responseDto = taskService.createTask(createTaskRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 태스크 수정
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @RequestBody UpdateTaskRequestDto updateTaskRequestDto,
            @PathVariable Long taskId
    ) {
        TaskResponseDto responseDto = taskService.updateTask(updateTaskRequestDto, taskId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 태스크 상태 변경
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @RequestBody TaskStatusUpdateRequestDto requestDto,
            @PathVariable Long taskId
    ) {
        TaskResponseDto responseDto = taskService.updateTaskStatus(requestDto, taskId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK); // 200
    }

    // 전체 태스크 조회
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // 단건 태스크 조회
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long taskId) {
        TaskResponseDto responseDto = taskService.getTaskById(taskId);
        return ResponseEntity.ok(responseDto);
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
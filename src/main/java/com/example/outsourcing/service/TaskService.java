package com.example.outsourcing.service;

import com.example.outsourcing.dto.task.TaskRequestDto;
import com.example.outsourcing.dto.task.TaskResponseDto;
import com.example.outsourcing.dto.task.TaskStatusUpdateRequestDto;
import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.global.exception.TaskNotFoundException;
import com.example.outsourcing.repository.TaskRepository;
import com.example.outsourcing.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository; // db
    private final UserRepository userRepository; // 구현하셨다고 가정

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // 태스크 생성
    @Transactional
    public TaskResponseDto createTask(TaskRequestDto requestDto, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        TaskEntity task = new TaskEntity(user, requestDto.getTitle(), requestDto.getTaskContent(), requestDto.getTaskStatus());

        TaskEntity savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask);
    }

    // 태스크 수정
    @Transactional
    public TaskResponseDto updateTask(TaskRequestDto requestDto, Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));

        //제목/내용만 업데이트
        task.updateTask(requestDto.getTitle(), requestDto.getTaskContent());
        return new TaskResponseDto(task);
    }

    // 태스크 상태 변경
    @Transactional
    public TaskResponseDto updateTaskStatus(TaskStatusUpdateRequestDto requestDto, Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));

        task.updateStatus(requestDto.getTaskStatus());
        return new TaskResponseDto(task);
    }

    // 태스크 삭제
    @Transactional
    public void deleteTask(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));

        taskRepository.delete(task);
    }
}
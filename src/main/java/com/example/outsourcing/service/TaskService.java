package com.example.outsourcing.service;

import com.example.outsourcing.dto.task.TaskRequestDto;
import com.example.outsourcing.dto.task.TaskResponseDto;
import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.repository.TaskRepository;
import com.example.outsourcing.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // final 필드 자동생성
public class TaskService {

    private final TaskRepository taskRepository; // db
    private final UserRepository userRepository; // 구현하셨다고 가정

    // 태스크 생성
    @Transactional
    public TaskResponseDto createTask(TaskRequestDto requestDto, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        TaskEntity task = new TaskEntity(user, requestDto.getTitle(), requestDto.getTaskContent(), requestDto.getStatus());

        TaskEntity savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask);
    }

    // 태스크 수정
    public TaskResponseDto updateTask(TaskRequestDto requestDto, Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("해당 태스크가 존재하지 않습니다."));

        task.updateTask(requestDto.getTitle(), requestDto.getTaskContent(), requestDto.getStatus());

        return new TaskResponseDto(task);
    }

    // 태스크 상태 변경

    // 태스크 삭제


}





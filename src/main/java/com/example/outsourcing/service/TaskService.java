package com.example.outsourcing.service;

import com.example.outsourcing.dto.task.*;
import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.TaskStatus;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.global.exception.TaskNotFoundException;
import com.example.outsourcing.repository.LogRepository;
import com.example.outsourcing.repository.TaskRepository;
import com.example.outsourcing.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository; // db
    private final UserRepository userRepository; // 구현하셨다고 가정
    private final LogRepository logRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository,LogRepository logRepository ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    // 태스크 생성
    @Transactional
    public TaskResponseDto createTask(CreateTaskRequestDto createTaskRequestDto, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        TaskEntity task = new TaskEntity(user, createTaskRequestDto.getTitle(), createTaskRequestDto.getTaskContent(), createTaskRequestDto.getTaskStatus());
        //로그 생성
        LogEntity logtaskcreate = LogEntity.logFromTaskCreate(
                user,
                "작업 생성",
                "작업이 생성되었습니다."
        );
        logRepository.save(logtaskcreate);

        TaskEntity savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask);
    }

    // 태스크 수정
    @Transactional
    public TaskResponseDto updateTask(UpdateTaskRequestDto dto, Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .filter(taskEntity -> !taskEntity.isDeleted())
                .orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));

        task.updateTask(dto.getTitle(), dto.getTaskContent());
        return new TaskResponseDto(task);
    }

    // 태스크 상태 변경
    @Transactional
    public TaskResponseDto updateTaskStatus(TaskStatusUpdateRequestDto dto, Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .filter(taskEntity -> !taskEntity.isDeleted())
                .orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));

        task.updateStatus(dto.getTaskStatus());
        return new TaskResponseDto(task);
    }

    // 전체 태스크 조회
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .filter(taskEntity -> !taskEntity.isDeleted()) // 삭제 안 된 태스크만 필터링
                .map(TaskResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단건 태스크 조회
    public TaskResponseDto getTaskById(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId)
                .filter(taskEntity -> !taskEntity.isDeleted())
                .orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));
        return new TaskResponseDto(task);
    }

    // 태스크 삭제
    @Transactional
    public void deleteTask(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));

        task.softDelete();
    }









    //대시보드
    public List<DashboardDto> getDashboardStatus() {
        //TODO, IN_PROGRESS, DONE 별 개수 조회
        Map<TaskStatus, Long> countMap = Arrays.stream(TaskStatus.values())
                .collect(Collectors.toMap(
                        Function.identity(),
                        status -> taskRepository.countByTaskStatus(status)
                ));

        //전체 개수 계산
        long total = countMap.values().stream().mapToLong(Long::longValue).sum();

        //DashboardDto 리스트로 변환
        return countMap.entrySet().stream()
                .map(entry -> new DashboardDto(
                        entry.getKey(),
                        entry.getValue(),
                        total == 0 ? 0.0 : Math.round((entry.getValue() * 1000.0 / total)) / 10.0
                ))
                .collect(Collectors.toList());
    }
}
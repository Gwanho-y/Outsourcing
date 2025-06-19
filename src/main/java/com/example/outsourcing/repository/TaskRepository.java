package com.example.outsourcing.repository;

import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    // 리스트로 유저 조회
    List<TaskEntity> findAllByUserId(Long userId);




    //status 기준으로 카운팅
    long countByTaskStatus(TaskStatus status);
}

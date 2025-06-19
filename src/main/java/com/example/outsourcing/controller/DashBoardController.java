package com.example.outsourcing.controller;

import com.example.outsourcing.entity.TaskStatus;
import com.example.outsourcing.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashBoardController {
    private final TaskRepository taskRepository;

    public DashBoardController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void printStatusCounts() {
        //시작 개수
        long todoCount = taskRepository.countByTaskStatus(TaskStatus.TODO);
        //진행중 개수
        long inProgressCount = taskRepository.countByTaskStatus(TaskStatus.IN_PROGRESS);
        //완료 개수
        long doneCount = taskRepository.countByTaskStatus(TaskStatus.DONE);
        //총 개수
        long countAll = todoCount + inProgressCount + doneCount;
        if (countAll == 0) {
            System.err.println("오류: 전체 Task 개수가 0이라 퍼센티지를 계산할 수 없습니다.");
            return;
        }
        //투두 퍼센티지
        long todoPercent = todoCount/countAll*100;
        //진행중 퍼센티지
        long inProgressPercent = inProgressCount/countAll*100;
        //완료 퍼센티지
        long donePercent = doneCount/countAll*100;


        System.out.printf("TODO: 약%d, IN_PROGRESS: 약%d, DONE: 약%d",
                todoPercent, inProgressPercent, donePercent);
    }
}

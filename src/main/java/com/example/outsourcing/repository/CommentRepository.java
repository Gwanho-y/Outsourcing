package com.example.outsourcing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.example.outsourcing.entity.CommentEntity;
import com.example.outsourcing.entity.TaskEntity;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByTaskIdAndIsDeletedFalseOrderByCreatedAtDesc(TaskEntity task); /*taskId에 딸린 댓글 검색*/
    List<CommentEntity> findByContentContainingAndIsDeletedFalse(String content); /*내용에 대한 Like 검색*/
}
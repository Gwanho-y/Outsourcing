package com.example.outsourcing.service;

import com.example.outsourcing.dto.CommentDto;
import com.example.outsourcing.entity.CommentEntity;
import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    /*댓글 생성*/
    public CommentEntity create(CommentDto dto) {
        TaskEntity task = taskRepository.findById(dto.getTaskId()).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        UserEntity user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        CommentEntity comment = new CommentEntity();
        comment.setUserId(user);
        comment.setTaskId(task);
        comment.setContent(dto.getContent());

        return commentRepository.save(comment);
    }

    /*댓글 수정*/
    public CommentEntity update(Long commentId, String newContent) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.setContent(newContent);

        return commentRepository.save(comment);
    }

    /*댓글 삭제*/
    public void delete(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.setIsDeleted(true);
        comment.setDeletedAt();
        commentRepository.save(comment);
    }

    /*태스크에 딸린 전체 댓글 조회*/
    public List<CommentEntity> commentListByTask(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return commentRepository.findByTaskAndDeletedFalseOrderByCreatedAtDesc(task);
    }

    /*내용에 대한 Like 검색*/
    public List<CommentEntity> search(String keyword) {
        return commentRepository.findByContentContainingAndDeletedFalse(keyword);
    }


}

package com.example.outsourcing.service;

import com.example.outsourcing.dto.comment.*;
import com.example.outsourcing.entity.CommentEntity;
import com.example.outsourcing.entity.TaskEntity;
import com.example.outsourcing.entity.UserEntity;
import com.example.outsourcing.global.exception.CommentNotFoundException;
import com.example.outsourcing.global.exception.CommentUserMismatchException;
import com.example.outsourcing.global.exception.TaskNotFoundException;
import com.example.outsourcing.repository.CommentRepository;
import com.example.outsourcing.repository.TaskRepository;
import com.example.outsourcing.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          TaskRepository taskRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /*댓글 생성*/
    @Transactional
    public CommentCreateResponseDto create(CommentCreateRequestDto dto) {
        TaskEntity taskId = taskRepository.findById(dto.getTaskId()).orElseThrow(() -> new TaskNotFoundException("ID=" + dto.getTaskId()));
        UserEntity userId = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        CommentEntity comment = new CommentEntity(taskId, userId, dto.getContent());
        commentRepository.save(comment);

        return new CommentCreateResponseDto(comment);
    }

    /*댓글 수정*/
    @Transactional
    public CommentUpdateResponsetDto update(Long commentId, CommentUpdateRequestDto dto) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException());
        if(!comment.getUserId().getId().equals(dto.getUserId())) {
            throw new CommentUserMismatchException();
        }
        comment.updateComment(dto.getContent());

        return new CommentUpdateResponsetDto(comment);
    }

    /*댓글 삭제*/
    @Transactional
    public CommentDeleteResponseDto delete(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException());

        comment.delete();

        return new CommentDeleteResponseDto(comment);
    }

    /*태스크에 딸린 전체 댓글 조회*/
    public List<CommentDto> findCommentsByTask(Long taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("ID=" + taskId));
        List<CommentEntity> entityList = commentRepository.findByTaskIdAndIsDeletedFalseOrderByCreatedAtDesc(task);
        List<CommentDto> responseDto = entityList.stream().
                map(entity -> new CommentDto(
                        entity.getCommentId(), entity.getTaskId().getTaskId(), entity.getUserId().getId(), entity.getContent(), entity.getCreatedAt(), entity.getUpdatedAt())).
                collect(Collectors.toList());
        return responseDto;
    }

    /*내용에 대한 Like 검색*/
    public List<CommentDto> findCommentsByKeyword(String keyword) {
        List<CommentEntity> entityList = commentRepository.findByContentContainingAndIsDeletedFalse(keyword);
        List<CommentDto> responseDto = entityList.stream()
                .map(entity -> new CommentDto(
                        entity.getCommentId(),
                        entity.getTaskId().getTaskId(),
                        entity.getUserId().getId(),
                        entity.getContent(),
                        entity.getCreatedAt(),
                        entity.getUpdatedAt()))
                .collect(Collectors.toList());
        return responseDto;
    }
}

package com.example.outsourcing.controller;

import com.example.outsourcing.dto.comment.*;
import com.example.outsourcing.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired private CommentService service;

    /*댓글 생성*/
    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> create(@RequestBody CommentCreateRequestDto dto) {
        CommentCreateResponseDto responseDto = service.create(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /*댓글 수정*/
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponsetDto> update(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto dto) {
        CommentUpdateResponsetDto responsetDto = service.update(commentId, dto);
        return new ResponseEntity<>(responsetDto, HttpStatus.OK);
    }

    /*댓글 삭제*/
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDeleteResponseDto> delete(@PathVariable Long commentId) {
        CommentDeleteResponseDto responseDto = service.delete(commentId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /*태스크에 딸린 댓글 전체조회*/
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentDto>> list(@PathVariable Long taskId) {
        List<CommentDto> responseDto = service.findCommentsByTask(taskId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /*내용에 대한 Like 검색*/
    @GetMapping("/search")
    public ResponseEntity<List<CommentDto>> search(@RequestParam("keyword") String keyword) {
        List<CommentDto> responseDto = service.findCommentsByKeyword(keyword);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
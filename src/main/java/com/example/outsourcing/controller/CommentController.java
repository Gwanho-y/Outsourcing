package com.example.outsourcing.controller;

import com.example.outsourcing.dto.CommentDto;
import com.example.outsourcing.entity.CommentEntity;
import com.example.outsourcing.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired private CommentService service;

    /*댓글 생성*/
    @PostMapping
    public ResponseEntity<CommentEntity> create(@RequestBody CommentDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }
    
    /*댓글 수정*/
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentEntity> update(@PathVariable Long commentId, @RequestBody CommentDto dto) {
        return ResponseEntity.ok(service.update(commentId, dto.getContent()));
    }
    
    /*댓글 삭제*/
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        service.delete(commentId);
        return ResponseEntity.noContent().build();
    }
    
    /*태스크에 딸린 댓글 전체조회*/
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<CommentEntity>> list(@PathVariable Long taskId) {
        return ResponseEntity.ok(service.listByTask(taskId));
    }

    /*내용에 대한 Like 검색*/
    @GetMapping("/search")
    public ResponseEntity<List<CommentEntity>> search(@RequestParam("keyword") String keyword) {
        List<CommentEntity> results = service.search(keyword);
        return ResponseEntity.ok(results);
    }
}

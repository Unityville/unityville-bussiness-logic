package com.example.Unityville.controller;

import com.example.Unityville.entities.Comment;
import com.example.Unityville.mappers.Mapper;
import com.example.Unityville.models.CommentDTO;
import com.example.Unityville.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAllComments() {
        return ResponseEntity.ok(commentService.findAll()
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<CommentDTO> saveComment(@PathVariable Long userId, @RequestBody CommentDTO commentDTO) {
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .createTimestamp(Timestamp.from(Instant.now()))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.convertToDTO(commentService.save(comment, userId)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long userId) {
        return ResponseEntity.ok(mapper.convertToDTO(commentService.deleteComment(userId)));
    }
}

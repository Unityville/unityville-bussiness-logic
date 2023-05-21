package com.example.Unityville.controller;

import com.example.Unityville.entities.Post;
import com.example.Unityville.mappers.Mapper;
import com.example.Unityville.models.post.PostDTO;
import com.example.Unityville.models.post.PostEditDTO;
import com.example.Unityville.models.post.PostLikesDTO;
import com.example.Unityville.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<PostDTO>> findAllPosts() {
        return ResponseEntity.ok(postService.findAll()
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PostDTO> savePost(@RequestBody PostDTO postDTO) {
        Post post = Post.builder()
                .title(postDTO.getTitle())
                .text(postDTO.getText())
                .image(postDTO.getImage())
                .isPinned(postDTO.isPinned())
                .user((postDTO.getUser() == null) ? null : mapper.convertToEntity(postDTO.getUser()))
                .communityOfPractice((postDTO.getCommunityOfPractice() == null) ? null :
                        mapper.convertToEntity(postDTO.getCommunityOfPractice()))
                .build();
        return ResponseEntity.ok(mapper.convertToDTO(postService.save(post)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.convertToDTO(postService.deletePost(id)));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<PostDTO> editPost(@PathVariable Long id, @RequestBody PostEditDTO postEditDTO) {
        Post post = Post.builder()
                .title(postEditDTO.getTitle())
                .text(postEditDTO.getText())
                .isPinned(postEditDTO.isPinned())
                .image(postEditDTO.getImage())
                .build();
        return ResponseEntity.ok(mapper.convertToDTO(postService.editPost(id, post)));
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<PostLikesDTO> likesFromPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getLikesFromPost(id));
    }
}

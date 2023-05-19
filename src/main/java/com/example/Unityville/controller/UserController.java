package com.example.Unityville.controller;

import com.example.Unityville.entities.Comment;
import com.example.Unityville.entities.User;
import com.example.Unityville.mappers.Mapper;
import com.example.Unityville.models.CommentDTO;
import com.example.Unityville.models.user.UserDTO;
import com.example.Unityville.models.user.UserLikedPostsDTO;
import com.example.Unityville.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Mapper mapper;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll()
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        return ResponseEntity.ok(mapper.convertToDTO(userService.findUserById(id)));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUsers(@RequestBody UserDTO user) {
        User userEntity = User.builder()
                .username(user.getUsername())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.convertToDTO(userService.save(userEntity)));
    }

    @PatchMapping("/{userId}/follow/{copID}")
    public ResponseEntity<UserDTO> followCOP(@PathVariable Long userId, @PathVariable Long copID) {
        return ResponseEntity.ok(mapper.convertToDTO(userService.patchUserWithFollowedCOP(userId, copID)));
    }

    @PatchMapping("/{userId}/unfollow/{copID}")
    public ResponseEntity<UserDTO> unfollowCOP(@PathVariable Long userId, @PathVariable Long copID) {
        return ResponseEntity.ok(mapper.convertToDTO(userService.patchUserWithUnfollowedCOP(userId, copID)));
    }

    @PatchMapping("/{userId}/posts/like/postId}")
    public ResponseEntity<UserLikedPostsDTO> likePost(@PathVariable Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok(mapper.convertToUserLikedDTO(userService.patchUserWithLikedPost(userId, postId)));
    }

    @PatchMapping("/{userId}/posts/dislike/{postId}")
    public ResponseEntity<UserLikedPostsDTO> dislikePost(@PathVariable Long userId, @PathVariable Long postId) {
        return ResponseEntity.ok(mapper.convertToUserLikedDTO(userService.patchUserWithDislikedPost(userId, postId)));
    }
    @PatchMapping("/{userId}/comments/like/{commentId}")
    public ResponseEntity<UserLikedPostsDTO> likeComments(@PathVariable Long userId, @PathVariable Long commentId) {
        return ResponseEntity.ok(mapper.convertToUserLikedDTO(userService.patchUserWithLikedComment(userId, commentId)));
    }

    @PatchMapping("/{userId}/comments/dislike/{commentId}")
    public ResponseEntity<UserLikedPostsDTO> dislikeComment(@PathVariable Long userId, @PathVariable Long commentId) {
        return ResponseEntity.ok(mapper.convertToUserLikedDTO(userService.patchUserWithDislikedComment(userId, commentId)));
    }

    @PatchMapping("/{userId}/comments/{commentId}")
    public ResponseEntity<UserLikedPostsDTO> editComment(@PathVariable Long userId, @PathVariable Long commentId,
                                                         @RequestBody CommentDTO newComment) {
        Comment commentEntity = Comment.builder()
                .content(newComment.getContent())
                .build();

        return ResponseEntity.ok(mapper.convertToUserLikedDTO(userService.editComment(userId, commentId, commentEntity)));
    }
}

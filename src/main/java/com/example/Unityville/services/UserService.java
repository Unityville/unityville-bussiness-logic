package com.example.Unityville.services;

import com.example.Unityville.entities.*;
import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NotFoundException;
import com.example.Unityville.exceptions.NullArgumentsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IOCallerService ioCallerService;

    public List<User> findAll() {
        return ioCallerService.findAllUsers();
    }

    public User save(User user) {
        var u = ioCallerService.findUserByUsername(user.getUsername());

        if (user.getUsername() == null) {
            throw new NullArgumentsException("illegal args");
        }

        if (findAll().contains(u)) {
            throw new AlreadyInsertException("too bad! it's already inserted");
        }

        return ioCallerService.saveUser(user);
    }

    public User patchUserWithFollowedCOP(Long userId, Long copId) {
        User user = ioCallerService.getUserReferenceById(userId);
        CommunityOfPractice cop = ioCallerService.getCOPById(copId);

        if (!user.getCommunityOfPractices().contains(cop)) {
            user.getCommunityOfPractices().add(cop);
        }

        return ioCallerService.saveUser(user);
    }

    public User patchUserWithUnfollowedCOP(Long userId, Long copId) {
        var user = ioCallerService.getUserReferenceById(userId);
        var cop = ioCallerService.getCOPById(copId);

        user.getCommunityOfPractices().remove(cop);
        cop.getUsers().remove(user);

        return ioCallerService.saveUser(user);
    }

    public User patchUserWithLikedPost(Long userId, Long postId) {
        User user = ioCallerService.findUserById(userId);
        Post post = ioCallerService.findPostById(postId);

        if (user == null || post == null) {
            throw new NotFoundException("Id not available in the database!");
        }

        Like like = Like.builder()
                .user(user)
                .post(post)
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        ioCallerService.saveLike(like);

        return ioCallerService.getUserReferenceById(userId);
    }

    public User patchUserWithLikedComment(Long userId, Long commentId) {
        User user = ioCallerService.findUserById(userId);
        Comment comment = ioCallerService.findCommentById(commentId);

        if (user == null || comment == null) {
            throw new NotFoundException("Id not available in the database!");
        }

        Like like = Like.builder()
                .user(user)
                .comment(comment)
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        ioCallerService.saveLike(like);

        return ioCallerService.getUserReferenceById(userId);
    }

    @Transactional
    public User patchUserWithDislikedPost(Long userId, Long postId) {
        ioCallerService.deleteLikeByUserAndPost(userId, postId);

        return ioCallerService.getUserReferenceById(userId);
    }

    @Transactional
    public User patchUserWithDislikedComment(Long userId, Long commentId) {
        ioCallerService.deleteLikeByUserAndComment(userId, commentId);

        return ioCallerService.getUserReferenceById(userId);
    }

    public User findUserById(Long id) {
        User u = ioCallerService.findUserById(id);

        if (u == null) {
            throw new NotFoundException("Id not available in the database!");
        }

        return u;
    }

    public User editComment(Long userId, Long commentId, Comment newComment) {
        User user = ioCallerService.findUserById(userId);
        Comment comment = ioCallerService.findCommentById(commentId);

        if (user == null || comment == null) {
            throw new NotFoundException("Id not available in the database!");
        }

        comment.setContent(newComment.getContent());
        comment.setCreateTimestamp(Timestamp.from(Instant.now()));

        ioCallerService.saveComment(comment);

        return ioCallerService.getUserReferenceById(userId);
    }
}

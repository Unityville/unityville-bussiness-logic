package com.example.Unityville.services;

import com.example.Unityville.entities.*;
import com.example.Unityville.exceptions.NotFoundException;
import com.example.Unityville.exceptions.NullArgumentsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IOCallerService ioCallerService;

    public List<User> findAll() {
        return ioCallerService.findAllUsers();
    }

    public User save(User user) {
        User userWithSameUsername = ioCallerService.findUserByUsername(user.getUsername());

        if (userWithSameUsername.getUsername() != null) {
            throw new NullArgumentsException("This username already exists!");
        }

        return ioCallerService.saveUser(user);
    }

    public User updateUserWithFollowedCOP(Long userId, Long copId) {
        User user = ioCallerService.findUserById(userId);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + userId + " does not exists!");
        }

        CommunityOfPractice cop = ioCallerService.getCOPById(copId);

        if (cop.getName() == null) {
            throw new NotFoundException("COP with id " + copId + " does not exists!");
        }

        if (!user.getCommunityOfPractices().contains(cop)) {
            user.getCommunityOfPractices().add(cop);
        }

        ioCallerService.putUser(user);

        return user;
    }

    public User updateUserWithUnfollowedCOP(Long userId, Long copId) {
        User user = ioCallerService.findUserById(userId);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + userId + " does not exists!");
        }

        CommunityOfPractice cop = ioCallerService.getCOPById(copId);

        if (cop.getName() == null) {
            throw new NotFoundException("COP with id " + copId + " does not exists!");
        }

        user.getCommunityOfPractices().removeIf(c -> Objects.equals(c.getId(), copId));

        cop.getUsers().removeIf(u -> Objects.equals(u.getId(), userId));

        ioCallerService.putUser(user);

        return user;
    }

    public User updateUserWithLikedPost(Long userId, Long postId) {
        User user = ioCallerService.findUserById(userId);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + userId + " does not exists!");
        }

        Post post = ioCallerService.findPostById(postId);

        if (post.getTitle() == null) {
            throw new NotFoundException("Post with id " + postId + " does not exists!");
        }

        Like like = Like.builder()
                .user(user)
                .post(post)
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        ioCallerService.saveLike(like);

        return ioCallerService.findUserById(userId);
    }

    public User updateUserWithLikedComment(Long userId, Long commentId) {
        User user = ioCallerService.findUserById(userId);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + userId + " does not exists!");
        }

        Comment comment = ioCallerService.findCommentById(commentId);

        if (comment.getContent() == null) {
            throw new NotFoundException("Comment with id " + commentId + " does not exists!");
        }

        Like like = Like.builder()
                .user(user)
                .comment(comment)
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        ioCallerService.saveLike(like);

        return ioCallerService.getUserReferenceById(userId);
    }

    public User updateUserWithDislikedPost(Long userId, Long postId) {
        User user = ioCallerService.findUserById(userId);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + userId + " does not exists!");
        }

        Post post = ioCallerService.findPostById(postId);

        if (post.getTitle() == null) {
            throw new NotFoundException("Post with id " + postId + " does not exists!");
        }

        ioCallerService.deleteLikeByUserAndPost(userId, postId);

        return ioCallerService.getUserReferenceById(userId);
    }

    @Transactional
    public User updateUserWithDislikedComment(Long userId, Long commentId) {
        User user = ioCallerService.findUserById(userId);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + userId + " does not exists!");
        }

        Comment comment = ioCallerService.findCommentById(commentId);

        if (comment.getContent() == null) {
            throw new NotFoundException("Post with id " + commentId + " does not exists!");
        }

        ioCallerService.deleteLikeByUserAndComment(userId, commentId);

        return ioCallerService.getUserReferenceById(userId);
    }

    public User findUserById(Long id) {
        User user = ioCallerService.findUserById(id);

        if (user.getUsername() == null) {
            throw new NotFoundException("User with id " + id + " not available!");
        }

        return user;
    }

    public User editComment(Long userId, Long commentId, Comment newComment) {
        User user = ioCallerService.findUserById(userId);
        Comment comment = ioCallerService.findCommentById(commentId);

        if (user == null || comment == null) {
            throw new NotFoundException("Id not available in the database!");
        }

        comment.setContent(newComment.getContent());
        comment.setCreateTimestamp(Timestamp.from(Instant.now()));

        ioCallerService.putComment(comment);

        return ioCallerService.getUserReferenceById(userId);
    }

}

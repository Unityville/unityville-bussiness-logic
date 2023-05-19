package com.example.Unityville.services;

import com.example.Unityville.entities.Comment;
import com.example.Unityville.entities.Like;
import com.example.Unityville.entities.Post;
import com.example.Unityville.entities.User;
import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NotFoundException;
import com.example.Unityville.exceptions.NullArgumentsException;
import com.example.Unityville.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CommunityOfPracticeRepository communityOfPracticeRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        var u = userRepository.findUserByUsername(user.getUsername());

        if (user.getUsername() == null) {
            throw new NullArgumentsException("illegal args");
        }

        if (findAll().contains(u)) {
            throw new AlreadyInsertException("too bad! it's already inserted");
        }

        return userRepository.save(user);
    }

    public User getReferenceById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public User patchUserWithFollowedCOP(Long userId, Long copId) {
        var user = userRepository.getReferenceById(userId);
        var cop = communityOfPracticeRepository.getReferenceById(copId);

        if (!user.getCommunityOfPractices().contains(cop)) {
            user.getCommunityOfPractices().add(cop);
        }

        return userRepository.save(user);
    }

    public User patchUserWithUnfollowedCOP(Long userId, Long copId) {
        var user = userRepository.getReferenceById(userId);
        var cop = communityOfPracticeRepository.getReferenceById(copId);

        user.getCommunityOfPractices().remove(cop);
        cop.getUsers().remove(user);

        return userRepository.save(user);
    }

    public User patchUserWithLikedPost(Long userId, Long postId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty() || post.isEmpty()) {
            throw new NotFoundException("Id not available in the database!");
        }

        Like like = Like.builder()
                .user(user.get())
                .post(post.get())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        likeRepository.save(like);

        return userRepository.getReferenceById(userId);
    }

    public User patchUserWithLikedComment(Long userId, Long commentId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (user.isEmpty() || comment.isEmpty()) {
            throw new NotFoundException("Id not available in the database!");
        }

        Like like = Like.builder()
                .user(user.get())
                .comment(comment.get())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        likeRepository.save(like);

        return userRepository.getReferenceById(userId);
    }

    @Transactional
    public User patchUserWithDislikedPost(Long userId, Long postId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);

        if (user.isEmpty() || post.isEmpty()) {
            throw new NotFoundException("Id not available in the database!");
        }

        likeRepository.deleteLikeByUserAndPost(user.get(), post.get());

        return userRepository.getReferenceById(userId);
    }
    @Transactional
    public User patchUserWithDislikedComment(Long userId, Long commentId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (user.isEmpty() || comment.isEmpty()) {
            throw new NotFoundException("Id not available in the database!");
        }

        likeRepository.deleteLikeByUserAndComment(user.get(), comment.get());

        return userRepository.getReferenceById(userId);
    }

    public User findUserById(Long id) {
        Optional<User> u = userRepository.findById(id);

        if (u.isEmpty()) {
            throw new NotFoundException("Id not available in the database!");
        }

        return u.get();
    }

    public User editComment(Long userId, Long commentId, Comment newComment) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (user.isEmpty() || comment.isEmpty()) {
            throw new NotFoundException("Id not available in the database!");
        }

        comment.get().setContent(newComment.getContent());
        comment.get().setCreateTimestamp(Timestamp.from(Instant.now()));

        commentRepository.save(comment.get());

        return userRepository.getReferenceById(userId);
    }
}

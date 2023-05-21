package com.example.Unityville.services;

import com.example.Unityville.entities.Comment;
import com.example.Unityville.entities.User;
import com.example.Unityville.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final IOCallerService ioCallerService;

    public List<Comment> findAll() {
        return ioCallerService.findAllComments();
    }

    public Comment save(Comment comment, Long userId) {
        User user = ioCallerService.findUserById(userId);
        comment.setUser(user);

        ioCallerService.saveComment(comment);

        return comment;
    }

    public Comment deleteComment(Long id) {
        Comment comment = ioCallerService.findCommentById(id);

        if (comment.getContent() == null) {
            throw new NotFoundException("This post does not exists!");
        }

        ioCallerService.deleteCommentById(id);

        return comment;
    }
}

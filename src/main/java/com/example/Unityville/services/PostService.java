package com.example.Unityville.services;

import com.example.Unityville.entities.Post;
import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NotFoundException;
import com.example.Unityville.exceptions.NullArgumentsException;
import com.example.Unityville.models.post.PostLikesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {
    private final IOCallerService ioCallerService;

    public Post save(Post post) {
        if (post.getTitle() == null || post.getText() == null) {
            throw new NullArgumentsException("illegal args");
        }

        Post p = ioCallerService.getPostByTitle(post.getTitle());

        if (p.getTitle() != null) {
            throw new AlreadyInsertException("too bad! it's already inserted");
        }

        return ioCallerService.savePost(post);
    }

    public Post deletePost(Long id) {
        Post post = ioCallerService.findPostById(id);

        if (post.getTitle() == null) {
            throw new NotFoundException("This post does not exists!");
        }

        ioCallerService.deletePostById(id);

        return post;
    }

    public Post editPost(Long id, Post postEdit) {
        Post postFound = ioCallerService.findPostById(id);

        if (postFound.getTitle() == null) {
            throw new NotFoundException("The post that need to be edited does not exists!");
        }

        if (postEdit.getImage() != null) {
            postFound.setImage(postEdit.getImage());
        }
        if (postEdit.getText() != null) {
            postFound.setText(postEdit.getText());
        }
        if (postEdit.getTitle() != null) {
            postFound.setTitle(postEdit.getTitle());
        }
        if (postEdit.isPinned() != postFound.isPinned()) {
            postFound.setPinned(postEdit.isPinned());
        }
        postFound.setCreateTimestamp(Timestamp.from(Instant.now()));

        ioCallerService.putPost(postFound);
        return postFound;
    }

    public List<Post> findAll() {
        return ioCallerService.findAllPosts();
    }

    public PostLikesDTO getLikesFromPost(Long id) {
        Post post = ioCallerService.findPostById(id);

        if (post.getTitle() == null) {
            throw new NotFoundException("The post with id " + id + " does not exist!");
        }

        List<String> names = post.getLikes().stream().map(like -> like.getUser().getUsername()).collect(Collectors.toList());

        return PostLikesDTO.builder()
                .id(post.getId())
                .likes(names)
                .build();
    }
}

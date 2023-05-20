package com.example.Unityville.services;

import com.example.Unityville.entities.Post;
import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NullArgumentsException;
import com.example.Unityville.models.post.PostLikesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        if (p == null) {
            throw new AlreadyInsertException("too bad! it's already inserted");
        }

        return ioCallerService.savePost(post);
    }

    public Post deletePost(Long id) {
        Post post = ioCallerService.findPostById(id);

        ioCallerService.deletePostById(id);

        return post;
    }

    public Post editPost(Long id, Post postEdit) {
        Post postFound = ioCallerService.findPostById(id);
        postFound.setImage(postEdit.getImage());
        postFound.setText(postEdit.getText());
        postFound.setTitle(postEdit.getTitle());
        postFound.setPinned(postEdit.isPinned());

        ioCallerService.savePost(postFound);
        return postFound;
    }

    public List<Post> findAll() {
        return ioCallerService.findAllPosts();
    }

    public PostLikesDTO getLikesFromPost(Long id) {
        var post = ioCallerService.getPostReferenceById(id);
        List<String> names = post.getLikes().stream().map(like -> like.getUser().getUsername()).collect(Collectors.toList());

        return PostLikesDTO.builder()
                .id(post.getId())
                .likes(names)
                .build();
    }
}

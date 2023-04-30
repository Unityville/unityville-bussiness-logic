package com.example.Unityville.services;

import com.example.Unityville.entities.Post;
import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NullArgumentsException;
import com.example.Unityville.models.post.PostLikesDTO;
import com.example.Unityville.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public Post save(Post post) {
        if (post.getTitle() == null || post.getText() == null) {
            throw new NullArgumentsException("illegal args");
        }

        Optional<Post> p = postRepository.findPostByTitle(post.getTitle());

        if (p.isEmpty()) {
            throw new AlreadyInsertException("too bad! it's already inserted");
        }

        return postRepository.save(post);
    }

    public Post deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();

        postRepository.deleteById(id);

        return post;
    }

    public Post editPost(Long id, Post postEdit) {
        Post postFound = postRepository.findById(id).orElseThrow();
        postFound.setImage(postEdit.getImage());
        postFound.setText(postEdit.getText());
        postFound.setTitle(postEdit.getTitle());
        postFound.setPinned(postEdit.isPinned());

        postRepository.save(postFound);
        return postFound;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public PostLikesDTO getLikesFromPost(Long id) {
        var post = postRepository.getReferenceById(id);
        List<String> names = post.getLikes().stream().map(like -> like.getUser().getUsername()).collect(Collectors.toList());

        return PostLikesDTO.builder()
                .id(post.getId())
                .likes(names)
                .build();
    }
}

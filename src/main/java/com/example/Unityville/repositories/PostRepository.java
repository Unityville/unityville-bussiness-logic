package com.example.Unityville.repositories;

import com.example.Unityville.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAll();
    Optional<Post> findPostByTitle(String title);
    void deleteById(Long id);
    Optional<Post> findById(Long id);
}

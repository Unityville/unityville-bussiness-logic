package com.example.Unityville.repositories;

import com.example.Unityville.entities.Like;
import com.example.Unityville.entities.Post;
import com.example.Unityville.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    void deleteLikeByUserAndPost(User user, Post post);
}

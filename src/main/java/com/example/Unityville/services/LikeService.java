package com.example.Unityville.services;

import com.example.Unityville.entities.Like;
import com.example.Unityville.repositories.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public List<Like> findAll() {
        return likeRepository.findAll();
    }
}

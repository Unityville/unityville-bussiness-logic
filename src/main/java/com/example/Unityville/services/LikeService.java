package com.example.Unityville.services;

import com.example.Unityville.entities.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final IOCallerService ioCallerService;

    public List<Like> findAll() {
        return ioCallerService.findAllLikes();
    }
}

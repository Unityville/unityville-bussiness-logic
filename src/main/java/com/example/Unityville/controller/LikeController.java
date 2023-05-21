package com.example.Unityville.controller;

import com.example.Unityville.mappers.Mapper;
import com.example.Unityville.models.like.LikeAllDTO;
import com.example.Unityville.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<LikeAllDTO>> findAllLikes() {
        return ResponseEntity.ok(likeService.findAll()
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }
}

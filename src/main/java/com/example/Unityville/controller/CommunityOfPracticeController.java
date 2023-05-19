package com.example.Unityville.controller;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.mappers.Mapper;
import com.example.Unityville.models.cop.CommunityOfPracticeDTO;
import com.example.Unityville.models.post.PostDTO;
import com.example.Unityville.services.CommunityOfPracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cop")
@RequiredArgsConstructor
public class CommunityOfPracticeController {
    private final CommunityOfPracticeService communityOfPracticeService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<CommunityOfPracticeDTO>> getAllCOP() {
        return ResponseEntity.ok(communityOfPracticeService.findAll()
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CommunityOfPracticeDTO> saveCOP(@RequestBody CommunityOfPracticeDTO communityOfPracticeDTO) {
        CommunityOfPractice cop = CommunityOfPractice.builder()
                .name(communityOfPracticeDTO.getName())
                .description(communityOfPracticeDTO.getDescription())
                .group(mapper.convertToEntity(communityOfPracticeDTO.getGroup()))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.convertToDTO(communityOfPracticeService.save(cop)));
    }

    @GetMapping("/{id}/pinned")
    public ResponseEntity<List<PostDTO>> getPostsPinnedFromCop(@PathVariable Long id) {
        return ResponseEntity.ok(communityOfPracticeService.getPostsPinnedFromCop(id)
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }
}

package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Post;
import com.example.Unityville.repositories.CommunityOfPracticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityOfPracticeService {
    private final CommunityOfPracticeRepository communityOfPracticeRepository;

    public CommunityOfPractice save(CommunityOfPractice cop) {
        return communityOfPracticeRepository.save(cop);
    }

    public List<CommunityOfPractice> findAll() {
        return communityOfPracticeRepository.findAll();
    }

    public List<Post> getPostsPinnedFromCop(Long id) {
        CommunityOfPractice cop = communityOfPracticeRepository.getReferenceById(id);
        return cop.getPosts().stream().filter(Post::isPinned).collect(Collectors.toCollection(LinkedList::new));
    }
}

package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Post;
import com.example.Unityville.repositories.CommunityOfPracticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
        List<Post> posts = new LinkedList<>();
        CommunityOfPractice cop = communityOfPracticeRepository.getReferenceById(id);
        for (Post post : cop.getPosts()) {
            if (post.isPinned()){
                posts.add(post);
            }
        }
        return posts;
    }
}

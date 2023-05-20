package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Post;
import com.example.Unityville.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityOfPracticeService {
    private final IOCallerService ioCallerService;

    public CommunityOfPractice save(CommunityOfPractice cop) {
        return ioCallerService.saveCOP(cop);
    }

    public List<CommunityOfPractice> findAll() {
        return ioCallerService.findAllCOP();
    }

    public List<Post> getPostsPinnedFromCop(Long id) {
        CommunityOfPractice cop = ioCallerService.getCOPById(id);
        if (cop.getPosts() == null || cop.getPosts().isEmpty()) {
            throw new NotFoundException("There are no posts for this COP");
        }
        return cop.getPosts().stream().filter(Post::isPinned).collect(Collectors.toCollection(LinkedList::new));
    }
}

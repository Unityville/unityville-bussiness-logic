package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Group;
import com.example.Unityville.models.cop.CommunityOfPracticeAddOrRemoveDTO;
import com.example.Unityville.repositories.CommunityOfPracticeRepository;
import com.example.Unityville.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final CommunityOfPracticeRepository communityOfPracticeRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group getReferenceById(Long id) {
        return this.groupRepository.getReferenceById(id);
    }

    public Group addCOP(Long id, CommunityOfPracticeAddOrRemoveDTO copDTO) {
        Group group = groupRepository.getReferenceById(id);
        CommunityOfPractice cop = communityOfPracticeRepository.getCommunityOfPracticeByNameAndId(copDTO.getName(),
                copDTO.getId());

        if (!group.getCommunityOfPractices().contains(cop)) {
            group.getCommunityOfPractices().add(cop);
        }

        return groupRepository.save(group);
    }

    public Group removeCOP(Long id, CommunityOfPracticeAddOrRemoveDTO copDTO) {
        Group group = groupRepository.getReferenceById(id);
        CommunityOfPractice cop = communityOfPracticeRepository.getCommunityOfPracticeByNameAndId(copDTO.getName(),
                copDTO.getId());

        group.getCommunityOfPractices().remove(cop);
        return groupRepository.save(group);
    }
}

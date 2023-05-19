package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Group;
import com.example.Unityville.models.cop.CommunityOfPracticeAddOrRemoveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private IOCallerService ioCallerService;

    public List<Group> findAll() {
        return ioCallerService.findAllGroups();
    }

    public Group save(Group group) {
        return ioCallerService.saveGroup(group);
    }

    public Group getReferenceById(Long id) {
        return ioCallerService.getGroupById(id);
    }

    public Group addCOP(Long id, CommunityOfPracticeAddOrRemoveDTO copDTO) {
        Group group = ioCallerService.getGroupById(id);
        CommunityOfPractice cop = ioCallerService.getCommunityOfPracticeByNameAndId(copDTO.getName(),
                copDTO.getId());

        if (!group.getCommunityOfPractices().contains(cop)) {
            group.getCommunityOfPractices().add(cop);
        }

        return ioCallerService.saveGroup(group);
    }

    public Group removeCOP(Long id, CommunityOfPracticeAddOrRemoveDTO copDTO) {
        Group group = ioCallerService.getGroupById(id);
        CommunityOfPractice cop = ioCallerService.getCommunityOfPracticeByNameAndId(copDTO.getName(),
                copDTO.getId());

        group.getCommunityOfPractices().remove(cop);
        return ioCallerService.saveGroup(group);
    }
}

package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Group;
import com.example.Unityville.exceptions.AlreadyInsertException;
import com.example.Unityville.exceptions.NotFoundException;
import com.example.Unityville.models.cop.CommunityOfPracticeAddOrRemoveDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final IOCallerService ioCallerService;

    public List<Group> findAll() {
        return ioCallerService.findAllGroups();
    }

    public Group save(Group group) {
        return ioCallerService.saveGroup(group);
    }

    public Group getReferenceById(Long id) {
        Group group = ioCallerService.getGroupById(id);
        if (group.getName() == null) {
            throw new NotFoundException("Not found Group with id " + id);
        }
        return group;
    }

    public Group addCOP(Long groupId, CommunityOfPracticeAddOrRemoveDTO copId) throws JsonProcessingException {
        Group group = ioCallerService.getGroupById(groupId);
        CommunityOfPractice cop = ioCallerService.getCOPById(copId.getId());

        if (cop.getName() == null) {
            throw new NotFoundException("Not found COP with id " + groupId);
        }

        if (group.getName() == null) {
            throw new NotFoundException("Not found Group with id " + groupId);
        }

        if (group.getCommunityOfPractices() == null) {
            group.setCommunityOfPractices(new ArrayList<>());
        }

        if (!group.getCommunityOfPractices().contains(cop)) {
            cop.setGroup(group);
        } else {
            throw new AlreadyInsertException("This Group already inserts this cop!");
        }

        ioCallerService.putCOP(cop);

        return ioCallerService.getGroupById(group.getId());
    }

    public Group removeCOP(Long groupId, CommunityOfPracticeAddOrRemoveDTO copDTO) throws JsonProcessingException {
        Group group = ioCallerService.getGroupById(groupId);
        CommunityOfPractice cop = ioCallerService.getCOPById(copDTO.getId());

        if (cop.getName() == null) {
            throw new NotFoundException("Not found COP with id " + cop.getId());
        }

        if (group.getName() == null) {
            throw new NotFoundException("Not found Group with id " + groupId);
        }

        cop.setGroup(null);
        ioCallerService.putCOP(cop);

        return ioCallerService.getGroupById(groupId);
    }

}

package com.example.Unityville.controller;

import com.example.Unityville.entities.Group;
import com.example.Unityville.mappers.Mapper;
import com.example.Unityville.models.GroupDTO;
import com.example.Unityville.models.cop.CommunityOfPracticeAddOrRemoveDTO;
import com.example.Unityville.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final Mapper mapper;

    @GetMapping
    public ResponseEntity<List<GroupDTO>> findAllGroups() {
        return ResponseEntity.ok(groupService.findAll()
                .stream()
                .map(mapper::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long id) {
        Group group = groupService.getReferenceById(id);
        return ResponseEntity.ok(mapper.convertToDTO(group));
    }

    @PostMapping
    public ResponseEntity<GroupDTO> saveGroup(@RequestBody GroupDTO groupDTO) {
        Group group = Group.builder()
                .name(groupDTO.getName())
                .description(groupDTO.getDescription())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.convertToDTO(groupService.save(group)));
    }

    @PutMapping("/{id}/addCOP")
    public ResponseEntity<GroupDTO> addCOP(@PathVariable Long id, @RequestBody CommunityOfPracticeAddOrRemoveDTO copDTO) {
        return ResponseEntity.ok(mapper.convertToDTO(groupService.addCOP(id, copDTO)));
    }

    @PutMapping("/{groupId}/removeCOP")
    public ResponseEntity<GroupDTO> removeCOP(@PathVariable Long groupId, @RequestBody CommunityOfPracticeAddOrRemoveDTO copDTO) {
        return ResponseEntity.ok(mapper.convertToDTO(groupService.removeCOP(groupId, copDTO)));
    }
}

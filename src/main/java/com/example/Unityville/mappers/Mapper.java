package com.example.Unityville.mappers;

import com.example.Unityville.entities.*;
import com.example.Unityville.models.like.LikeAllDTO;
import com.example.Unityville.models.cop.CommunityOfPracticeDTO;
import com.example.Unityville.models.GroupDTO;
import com.example.Unityville.models.post.PostDTO;
import com.example.Unityville.models.post.PostEditDTO;
import com.example.Unityville.models.user.UserDTO;
import com.example.Unityville.models.user.UserLikedPostsDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {
    private final ModelMapper modelMapper;
    public UserDTO convertToDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public GroupDTO convertToDTO(Group group){
        return modelMapper.map(group, GroupDTO.class);
    }
    public PostDTO convertToDTO(Post post){
        return modelMapper.map(post, PostDTO.class);
    }
    public UserLikedPostsDTO convertToUserLikedDTO(User user){
        return modelMapper.map(user, UserLikedPostsDTO.class);
    }
    public CommunityOfPracticeDTO convertToDTO(CommunityOfPractice communityOfPractice){
        return modelMapper.map(communityOfPractice, CommunityOfPracticeDTO.class);
    }
    public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public CommunityOfPractice convertToEntity(CommunityOfPracticeDTO communityOfPracticeDTO){
        return modelMapper.map(communityOfPracticeDTO, CommunityOfPractice.class);
    }
    public Group convertToEntity(GroupDTO groupDTO) {
        return modelMapper.map(groupDTO, Group.class);
    }

    public LikeAllDTO convertToDTO(Like like) {
        return LikeAllDTO.builder()
                .id(like.getId())
                .timestamp(like.getTimestamp())
                .user((like.getUser() != null) ? like.getUser().getUsername() : null)
                .post((like.getPost() == null) ? null : PostEditDTO.builder()
                        .title(like.getPost().getTitle())
                        .isPinned(like.getPost().isPinned())
                        .text(like.getPost().getText())
                        .image(like.getPost().getText())
                        .createTimestamp(like.getPost().getCreateTimestamp())
                        .build())
                .build();
    }
}

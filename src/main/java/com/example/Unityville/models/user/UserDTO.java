package com.example.Unityville.models.user;

import com.example.Unityville.models.CommentDTO;
import com.example.Unityville.models.GroupDTO;
import com.example.Unityville.models.cop.CommunityOfPracticeAddOrRemoveDTO;
import com.example.Unityville.models.like.LikeDTO;
import com.example.Unityville.models.post.PostDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserDTO {
    private Long id;
    private String username;
    private List<GroupDTO> groups;
    private List<PostDTO> posts;
    private List<CommunityOfPracticeAddOrRemoveDTO> communityOfPractices;
    private List<LikeDTO> likes;
    private List<CommentDTO> comments;
}

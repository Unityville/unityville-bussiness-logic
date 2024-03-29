package com.example.Unityville.models.cop;

import com.example.Unityville.models.GroupDTO;
import com.example.Unityville.models.post.PostDTO;
import com.example.Unityville.models.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommunityOfPracticeDTO {
    private Long id;

    private String name;

    private String description;

    private List<UserDTO> users;

    private List<PostDTO> posts;

    private GroupDTO group;
}

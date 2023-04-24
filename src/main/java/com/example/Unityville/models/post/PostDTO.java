package com.example.Unityville.models.post;

import com.example.Unityville.models.user.UserDTO;
import com.example.Unityville.models.cop.CommunityOfPracticeDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PostDTO {
    private Long id;
    private String title;
    private String text;
    private boolean isPinned;
    private String image;
    private UserDTO user;
    private CommunityOfPracticeDTO communityOfPractice;
}

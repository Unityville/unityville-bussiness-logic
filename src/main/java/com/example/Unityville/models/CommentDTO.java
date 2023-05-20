package com.example.Unityville.models;

import com.example.Unityville.models.like.LikeDTO;
import com.example.Unityville.models.post.PostDTO;
import com.example.Unityville.models.user.UserLikedPostsDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommentDTO {
    private Long id;

    private String content;

    private Timestamp createTimestamp;

    private UserLikedPostsDTO user;

    private List<LikeDTO> likes;

    private PostDTO post;
}

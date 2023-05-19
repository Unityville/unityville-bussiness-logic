package com.example.Unityville.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String username;

    private List<Group> groups;

    private List<CommunityOfPractice> communityOfPractices;

    private List<Post> posts;

    private List<Like> likes;

    private List<Comment> comments;
}

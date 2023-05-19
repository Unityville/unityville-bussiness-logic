package com.example.Unityville.entities;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;

    private String title;

    private String text;

    private boolean isPinned;

    private Timestamp createTimestamp;

    private String image;

    private User user;

    private CommunityOfPractice communityOfPractice;

    private List<Like> likes;

    private List<Comment> comments;
}

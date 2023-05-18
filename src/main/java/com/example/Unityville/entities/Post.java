package com.example.Unityville.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

package com.example.Unityville.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
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
public class Comment {
    private Long id;

    private String content;

    private Timestamp createTimestamp;

    private User user;

    private List<Like> likes;

    private Post post;

    @JsonCreator
    public Comment(Long id) {
        this.id = id;
    }
}

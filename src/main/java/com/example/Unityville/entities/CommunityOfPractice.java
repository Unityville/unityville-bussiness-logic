package com.example.Unityville.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityOfPractice {
    private Long id;

    private String name;

    private String description;

    private List<User> users;

    private List<Post> posts;

    private Group group;

    @JsonCreator
    public CommunityOfPractice(Long id) {
        this.id = id;
    }
}

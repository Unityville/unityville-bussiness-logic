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
public class Group {
    private Long id;

    private String name;

    private String description;

    private List<User> users;

    private List<CommunityOfPractice> communityOfPractices;

    @JsonCreator
    public Group(Long id) {
        this.id = id;
    }
}

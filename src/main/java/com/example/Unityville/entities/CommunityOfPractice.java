package com.example.Unityville.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "communities_of_practice")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityOfPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "communityOfPractices")
    private List<User> users;

    @OneToMany(mappedBy = "communityOfPractice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}

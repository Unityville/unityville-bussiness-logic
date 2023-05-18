package com.example.Unityville.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private Long id;

    private Timestamp timestamp;

    private Post post;

    private User user;
}

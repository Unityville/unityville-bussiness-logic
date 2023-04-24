package com.example.Unityville.repositories;

import com.example.Unityville.entities.CommunityOfPractice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityOfPracticeRepository extends JpaRepository<CommunityOfPractice, Long> {
    CommunityOfPractice getCommunityOfPracticeByNameAndId(String name, Long id);
}

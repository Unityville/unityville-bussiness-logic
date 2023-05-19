package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import com.example.Unityville.entities.Group;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class IOCallerService {
    private final String IOUrl = "http://localhost:8070";

    public CommunityOfPractice saveCOP(CommunityOfPractice cop) {
        return new RestTemplate().postForObject(IOUrl + "/cop", cop, CommunityOfPractice.class);
    }

    public List<CommunityOfPractice> findAllCOP() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/cop", CommunityOfPractice[].class)));
    }

    public CommunityOfPractice getCOPById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/cops/{id}", CommunityOfPractice.class, id);
    }

    public List<Group> findAllGroups() {
        return Arrays.asList(Objects.requireNonNull
                (new RestTemplate().getForObject(IOUrl + "/groups", Group[].class)));
    }

    public Group saveGroup(Group group) {
        return new RestTemplate().postForObject(IOUrl + "/groups", group, Group.class);
    }

    public Group getGroupById(Long id) {
        return new RestTemplate().getForObject(IOUrl + "/groups/{id}", Group.class, id);
    }

    public CommunityOfPractice getCommunityOfPracticeByNameAndId(String name, Long id) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("id", String.valueOf(id));
        return new RestTemplate().getForObject(IOUrl + "/cops/{name}/{id}", CommunityOfPractice.class, params);
    }
}

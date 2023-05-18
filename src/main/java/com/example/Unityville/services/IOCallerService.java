package com.example.Unityville.services;

import com.example.Unityville.entities.CommunityOfPractice;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IOCallerService {
    private final String IOUrl = "http://localhost:8070";

    public CommunityOfPractice saveCOP(CommunityOfPractice cop) {
        return new RestTemplate().postForObject(IOUrl + "/cop",cop, CommunityOfPractice.class);
    }

}

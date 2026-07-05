package com.careerpathai.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class CareerProfileLoader {

    private final CareerKnowledgeBase knowledgeBase;

    public CareerProfileLoader(CareerKnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    @PostConstruct
    public void loadProfiles() {

        try {

            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream =
                    new ClassPathResource("career-profiles.json")
                            .getInputStream();

            Map<String, CareerProfile> profiles =
                    mapper.readValue(
                            inputStream,
                            new TypeReference<Map<String, CareerProfile>>() {
                            });

            profiles.forEach(knowledgeBase::addProfile);

            System.out.println("--------------------------------");
            System.out.println("Career Profiles Loaded");
            System.out.println("Profiles : " + profiles.size());
            System.out.println("--------------------------------");

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load career profiles",
                    e
            );
        }
    }
}
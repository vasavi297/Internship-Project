package com.careerpathai.profile;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CareerKnowledgeBase {

    private final Map<String, CareerProfile> profiles = new HashMap<>();

    public Map<String, CareerProfile> getProfiles() {
        return profiles;
    }

    public CareerProfile getProfile(String career) {
        return profiles.get(career.toLowerCase());
    }

    public void addProfile(String career, CareerProfile profile) {
        profiles.put(career.toLowerCase(), profile);
    }
}
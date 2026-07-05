package com.careerpathai.career.service;

import com.careerpathai.career.model.SkillGap;
import com.careerpathai.profile.CareerKnowledgeBase;
import com.careerpathai.profile.CareerProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillGapService {

    private final CareerKnowledgeBase knowledgeBase;

    public SkillGapService(CareerKnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public SkillGap analyze(String careerName, List<String> resumeSkills) {

        CareerProfile profile = knowledgeBase.getProfile(careerName);

        if (profile == null) {
            return new SkillGap(
                    careerName,
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        List<String> normalizedResumeSkills = resumeSkills.stream()
                .map(skill -> skill.toLowerCase().trim())
                .toList();

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        // Required Skills
        for (String skill : profile.getRequiredSkills()) {

            if (normalizedResumeSkills.contains(skill.toLowerCase())) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        // Important Skills
        for (String skill : profile.getImportantSkills()) {

            if (normalizedResumeSkills.contains(skill.toLowerCase())) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        return new SkillGap(
                careerName,
                matchedSkills,
                missingSkills
        );
    }
}
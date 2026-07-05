package com.careerpathai.career.service;

import com.careerpathai.career.model.CareerRecommendation;
import com.careerpathai.profile.CareerKnowledgeBase;
import com.careerpathai.profile.CareerProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CareerRecommendationService {

    private final CareerKnowledgeBase knowledgeBase;

    public CareerRecommendationService(
            CareerKnowledgeBase knowledgeBase) {

        this.knowledgeBase = knowledgeBase;
    }

    public List<CareerRecommendation> recommendCareers(
            List<String> resumeSkills) {

        List<String> normalizedResumeSkills =
                resumeSkills.stream()
                        .map(s -> s.toLowerCase().trim())
                        .toList();

        List<CareerRecommendation> recommendations =
                new ArrayList<>();

        knowledgeBase.getProfiles().forEach((career, profile) -> {

            int matched = 0;

            List<String> matchedSkills =
                    new ArrayList<>();

            for (String skill : profile.getRequiredSkills()) {

                if (normalizedResumeSkills.contains(
                        skill.toLowerCase())) {

                    matched++;
                    matchedSkills.add(skill);
                }
            }

            int totalSkills =
                    profile.getRequiredSkills().size();

            double percentage =
                    (matched * 100.0) / totalSkills;

            recommendations.add(

                    new CareerRecommendation(
                            career,
                            matched,
                            totalSkills,
                            percentage,
                            matchedSkills
                    )
            );

        });

        recommendations.sort(
                Comparator.comparingDouble(
                        CareerRecommendation::getMatchPercentage)
                        .reversed()
        );

        return recommendations;
    }
}
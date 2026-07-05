package com.careerpathai.roadmap.service;

import com.careerpathai.career.model.SkillGap;
import com.careerpathai.career.service.SkillGapService;
import com.careerpathai.roadmap.model.LearningRoadmap;
import com.careerpathai.roadmap.model.RoadmapStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearningRoadmapService {

    private final SkillGapService skillGapService;

    public LearningRoadmapService(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    public LearningRoadmap generateRoadmap(
            String career,
            List<String> resumeSkills) {

        SkillGap gap = skillGapService.analyze(career, resumeSkills);

        List<RoadmapStep> roadmap = new ArrayList<>();

        List<String> missingSkills = gap.getMissingSkills();

        int week = 1;

        for (int i = 0; i < missingSkills.size(); i += 2) {

            List<String> topics = new ArrayList<>();

            topics.add(missingSkills.get(i));

            if (i + 1 < missingSkills.size()) {
                topics.add(missingSkills.get(i + 1));
            }

            roadmap.add(new RoadmapStep(
                    week++,
                    topics
            ));
        }

        return new LearningRoadmap(
                career,
                roadmap
        );
    }
}
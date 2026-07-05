package com.careerpathai.analysis;

import com.careerpathai.career.model.CareerRecommendation;
import com.careerpathai.career.model.SkillGap;
import com.careerpathai.career.service.CareerRecommendationService;
import com.careerpathai.career.service.SkillGapService;
import com.careerpathai.parser.ParsedResume;
import com.careerpathai.parser.ResumeParserService;
import com.careerpathai.roadmap.model.LearningRoadmap;
import com.careerpathai.roadmap.service.LearningRoadmapService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ResumeAnalysisService {

    private final ResumeParserService parserService;
    private final CareerRecommendationService recommendationService;
    private final SkillGapService skillGapService;
    private final LearningRoadmapService roadmapService;

    public ResumeAnalysisService(
            ResumeParserService parserService,
            CareerRecommendationService recommendationService,
            SkillGapService skillGapService,
            LearningRoadmapService roadmapService) {

        this.parserService = parserService;
        this.recommendationService = recommendationService;
        this.skillGapService = skillGapService;
        this.roadmapService = roadmapService;
    }

    public ResumeAnalysisResponse analyze(File file) throws IOException {

        // Step 1: Parse Resume
        ParsedResume parsedResume = parserService.parseResume(file);

        List<String> skills = parsedResume.getSkills();

        // Step 2: Recommend Careers
        List<CareerRecommendation> careers = recommendationService.recommendCareers(skills);

        if (careers.isEmpty()) {
            ResumeAnalysisResponse response = new ResumeAnalysisResponse();
            response.setParsedResume(parsedResume);
            return response;
        }

        // Step 3: Best Career
        CareerRecommendation bestCareer = careers.get(0);

        // Step 4: Skill Gap
        SkillGap gap = skillGapService.analyze(
                bestCareer.getCareerName(),
                skills
        );

        // Step 5: Learning Roadmap
        LearningRoadmap roadmap =
                roadmapService.generateRoadmap(
                        bestCareer.getCareerName(),
                        skills
                );

        // Step 6: Final Response
        ResumeAnalysisResponse response =
                new ResumeAnalysisResponse();

        response.setParsedResume(parsedResume);
        response.setRecommendedCareer(bestCareer);
        response.setTopMissingSkills(gap.getMissingSkills());
        response.setRoadmap(roadmap);

        return response;
    }
}
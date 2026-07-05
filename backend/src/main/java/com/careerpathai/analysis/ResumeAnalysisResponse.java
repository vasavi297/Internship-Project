package com.careerpathai.analysis;

import com.careerpathai.parser.ParsedResume;
import com.careerpathai.career.model.CareerRecommendation;
import com.careerpathai.roadmap.model.LearningRoadmap;

import java.util.List;

public class ResumeAnalysisResponse {

    private ParsedResume parsedResume;

    private CareerRecommendation recommendedCareer;

    private List<String> topMissingSkills;

    private LearningRoadmap roadmap;

    public ResumeAnalysisResponse() {
    }

    public ParsedResume getParsedResume() {
        return parsedResume;
    }

    public void setParsedResume(ParsedResume parsedResume) {
        this.parsedResume = parsedResume;
    }

    public CareerRecommendation getRecommendedCareer() {
        return recommendedCareer;
    }

    public void setRecommendedCareer(CareerRecommendation recommendedCareer) {
        this.recommendedCareer = recommendedCareer;
    }

    public List<String> getTopMissingSkills() {
        return topMissingSkills;
    }

    public void setTopMissingSkills(List<String> topMissingSkills) {
        this.topMissingSkills = topMissingSkills;
    }

    public LearningRoadmap getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(LearningRoadmap roadmap) {
        this.roadmap = roadmap;
    }
}
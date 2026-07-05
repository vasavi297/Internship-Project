package com.careerpathai.analysis;

import com.careerpathai.career.model.CareerRecommendation;
import com.careerpathai.parser.ParsedResume;
import com.careerpathai.roadmap.model.LearningRoadmap;
import com.careerpathai.score.model.ResumeScore;

import java.util.ArrayList;
import java.util.List;

public class ResumeAnalysisResponse {

    private String resumeId;

    private ParsedResume parsedResume;

    private ResumeScore resumeScore;

    // Best Career Recommendation
    private CareerRecommendation recommendedCareer;

    // Top Career Recommendations
    private List<CareerRecommendation> careerRecommendations = new ArrayList<>();

    // Top Missing Skills
    private List<String> topMissingSkills = new ArrayList<>();

    // Learning Roadmap
    private LearningRoadmap roadmap;

    public ResumeAnalysisResponse() {
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public ParsedResume getParsedResume() {
        return parsedResume;
    }

    public void setParsedResume(ParsedResume parsedResume) {
        this.parsedResume = parsedResume;
    }

    public ResumeScore getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(ResumeScore resumeScore) {
        this.resumeScore = resumeScore;
    }

    public CareerRecommendation getRecommendedCareer() {
        return recommendedCareer;
    }

    public void setRecommendedCareer(CareerRecommendation recommendedCareer) {
        this.recommendedCareer = recommendedCareer;
    }

    public List<CareerRecommendation> getCareerRecommendations() {
        return careerRecommendations;
    }

    public void setCareerRecommendations(List<CareerRecommendation> careerRecommendations) {
        this.careerRecommendations = careerRecommendations;
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
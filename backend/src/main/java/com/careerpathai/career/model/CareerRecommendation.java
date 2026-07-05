package com.careerpathai.career.model;

import java.util.ArrayList;
import java.util.List;

public class CareerRecommendation {

    private String careerName;
    private int matchedSkills;
    private int totalSkills;
    private double matchPercentage;
    private List<String> matchedSkillNames = new ArrayList<>();

    public CareerRecommendation() {
    }

    public CareerRecommendation(String careerName,
                                int matchedSkills,
                                int totalSkills,
                                double matchPercentage,
                                List<String> matchedSkillNames) {

        this.careerName = careerName;
        this.matchedSkills = matchedSkills;
        this.totalSkills = totalSkills;
        this.matchPercentage = matchPercentage;
        this.matchedSkillNames = matchedSkillNames;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public int getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(int matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public int getTotalSkills() {
        return totalSkills;
    }

    public void setTotalSkills(int totalSkills) {
        this.totalSkills = totalSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public List<String> getMatchedSkillNames() {
        return matchedSkillNames;
    }

    public void setMatchedSkillNames(List<String> matchedSkillNames) {
        this.matchedSkillNames = matchedSkillNames;
    }
}
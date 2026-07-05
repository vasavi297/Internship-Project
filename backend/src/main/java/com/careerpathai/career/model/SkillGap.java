package com.careerpathai.career.model;

import java.util.ArrayList;
import java.util.List;

public class SkillGap {

    private String careerName;

    private List<String> matchedSkills = new ArrayList<>();

    private List<String> missingSkills = new ArrayList<>();

    public SkillGap() {
    }

    public SkillGap(String careerName,
                    List<String> matchedSkills,
                    List<String> missingSkills) {

        this.careerName = careerName;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
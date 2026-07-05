package com.careerpathai.score.model;

import java.util.ArrayList;
import java.util.List;

public class ResumeScore {

    private int overallScore;
    private List<String> missingSections = new ArrayList<>();

    public ResumeScore() {
    }

    public ResumeScore(int overallScore, List<String> missingSections) {
        this.overallScore = overallScore;
        this.missingSections = missingSections;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public List<String> getMissingSections() {
        return missingSections;
    }

    public void setMissingSections(List<String> missingSections) {
        this.missingSections = missingSections;
    }
}
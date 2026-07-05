package com.careerpathai.profile;

import java.util.ArrayList;
import java.util.List;

public class CareerProfile {

    private List<String> requiredSkills = new ArrayList<>();
    private List<String> importantSkills = new ArrayList<>();
    private List<String> interviewTopics = new ArrayList<>();
    private List<String> resumeKeywords = new ArrayList<>();

    private String salaryRange;
    private String difficulty;
    private String learningDuration;

    public CareerProfile() {
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getImportantSkills() {
        return importantSkills;
    }

    public void setImportantSkills(List<String> importantSkills) {
        this.importantSkills = importantSkills;
    }

    public List<String> getInterviewTopics() {
        return interviewTopics;
    }

    public void setInterviewTopics(List<String> interviewTopics) {
        this.interviewTopics = interviewTopics;
    }

    public List<String> getResumeKeywords() {
        return resumeKeywords;
    }

    public void setResumeKeywords(List<String> resumeKeywords) {
        this.resumeKeywords = resumeKeywords;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getLearningDuration() {
        return learningDuration;
    }

    public void setLearningDuration(String learningDuration) {
        this.learningDuration = learningDuration;
    }
}
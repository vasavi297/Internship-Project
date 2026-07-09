package com.careerpathai.dashboard;

public class DashboardResponse {

    private String candidateName;

    private int totalResumes;

    private int resumeScore;

    private String recommendedCareer;

    private double careerMatch;

    private int missingSkillCount;

    private String latestResume;

    private String lastAnalyzed;

    private String salary;

private String difficulty;

private String duration;

    public DashboardResponse() {
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public int getTotalResumes() {
        return totalResumes;
    }

    public void setTotalResumes(int totalResumes) {
        this.totalResumes = totalResumes;
    }

    public int getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(int resumeScore) {
        this.resumeScore = resumeScore;
    }

    public String getRecommendedCareer() {
        return recommendedCareer;
    }

    public void setRecommendedCareer(String recommendedCareer) {
        this.recommendedCareer = recommendedCareer;
    }

    public double getCareerMatch() {
        return careerMatch;
    }

    public void setCareerMatch(double careerMatch) {
        this.careerMatch = careerMatch;
    }

    public int getMissingSkillCount() {
        return missingSkillCount;
    }

    public void setMissingSkillCount(int missingSkillCount) {
        this.missingSkillCount = missingSkillCount;
    }

    public String getLatestResume() {
        return latestResume;
    }

    public void setLatestResume(String latestResume) {
        this.latestResume = latestResume;
    }

    public String getLastAnalyzed() {
        return lastAnalyzed;
    }

    public void setLastAnalyzed(String lastAnalyzed) {
        this.lastAnalyzed = lastAnalyzed;
    }
    public String getSalary() {
    return salary;
}

public void setSalary(String salary) {
    this.salary = salary;
}

public String getDifficulty() {
    return difficulty;
}

public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
}

public String getDuration() {
    return duration;
}

public void setDuration(String duration) {
    this.duration = duration;
}
}
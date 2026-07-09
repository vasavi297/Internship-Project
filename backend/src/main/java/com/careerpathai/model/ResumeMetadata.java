package com.careerpathai.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ResumeMetadata {

    private String resumeId;

    private String fileName;

    private String s3Key;

    private Long fileSize;

    private String status;

    private LocalDateTime uploadTime;

    private LocalDateTime analyzedTime;

    private String candidateName;

    private String email;

    private String recommendedCareer;

    private Integer resumeScore;

    private String analysisSummary;

    private String userId;
    private Double careerMatch;

    private Integer missingSkillCount;

    public ResumeMetadata() {

        this.resumeId = UUID.randomUUID().toString();
        this.uploadTime = LocalDateTime.now();

    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }


    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public LocalDateTime getAnalyzedTime() {
        return analyzedTime;
    }

    public void setAnalyzedTime(LocalDateTime analyzedTime) {
        this.analyzedTime = analyzedTime;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecommendedCareer() {
        return recommendedCareer;
    }

    public void setRecommendedCareer(String recommendedCareer) {
        this.recommendedCareer = recommendedCareer;
    }

    public Integer getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(Integer resumeScore) {
        this.resumeScore = resumeScore;
    }

    public String getAnalysisSummary() {
        return analysisSummary;
    }

    public void setAnalysisSummary(String analysisSummary) {
        this.analysisSummary = analysisSummary;
    }
    public String getUserId() {
    return userId;
    }

    public void setUserId(String userId) {
    this.userId = userId;
    }
   public Double getCareerMatch() {
    return careerMatch;
}

public void setCareerMatch(Double careerMatch) {
    this.careerMatch = careerMatch;
}

    public Integer getMissingSkillCount() {
    return missingSkillCount;
    }

    public void setMissingSkillCount(Integer missingSkillCount) {
    this.missingSkillCount = missingSkillCount;
    }

}
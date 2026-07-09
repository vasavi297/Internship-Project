package com.careerpathai.model;

import com.careerpathai.analysis.ResumeAnalysisResponse;

public class ResumeAnalysisDocument {

    private String resumeId;

    private ResumeAnalysisResponse analysis;

    public ResumeAnalysisDocument() {
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public ResumeAnalysisResponse getAnalysis() {
        return analysis;
    }

    public void setAnalysis(
            ResumeAnalysisResponse analysis) {

        this.analysis = analysis;
    }

}
package com.careerpathai.roadmap.model;

import java.util.ArrayList;
import java.util.List;

public class LearningRoadmap {

    private String career;

    private List<RoadmapStep> roadmap = new ArrayList<>();

    public LearningRoadmap() {
    }

    public LearningRoadmap(String career, List<RoadmapStep> roadmap) {
        this.career = career;
        this.roadmap = roadmap;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public List<RoadmapStep> getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(List<RoadmapStep> roadmap) {
        this.roadmap = roadmap;
    }
}
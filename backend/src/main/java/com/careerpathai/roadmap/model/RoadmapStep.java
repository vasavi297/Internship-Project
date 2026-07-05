package com.careerpathai.roadmap.model;

import java.util.ArrayList;
import java.util.List;

public class RoadmapStep {

    private int week;

    private List<String> topics = new ArrayList<>();

    public RoadmapStep() {
    }

    public RoadmapStep(int week, List<String> topics) {
        this.week = week;
        this.topics = topics;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
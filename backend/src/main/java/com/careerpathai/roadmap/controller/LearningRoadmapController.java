package com.careerpathai.roadmap.controller;

import com.careerpathai.roadmap.model.LearningRoadmap;
import com.careerpathai.roadmap.service.LearningRoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roadmap")
public class LearningRoadmapController {

    private final LearningRoadmapService service;

    public LearningRoadmapController(
            LearningRoadmapService service) {

        this.service = service;
    }

    @PostMapping("/{career}")
    public LearningRoadmap roadmap(
            @PathVariable String career,
            @RequestBody List<String> skills) {

        return service.generateRoadmap(
                career,
                skills
        );
    }
}
package com.careerpathai.career.controller;

import com.careerpathai.career.model.SkillGap;
import com.careerpathai.career.service.SkillGapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/career")
public class SkillGapController {

    private final SkillGapService service;

    public SkillGapController(SkillGapService service) {
        this.service = service;
    }

    @PostMapping("/skill-gap/{career}")
    public SkillGap analyze(
            @PathVariable String career,
            @RequestBody List<String> skills) {

        return service.analyze(career, skills);
    }
}
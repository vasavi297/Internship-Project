package com.careerpathai.score.service;

import com.careerpathai.parser.ParsedResume;
import com.careerpathai.score.model.ResumeScore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeScoreService {

    public ResumeScore calculateScore(ParsedResume resume) {

        int score = 0;

        List<String> missing = new ArrayList<>();

        if (resume.getName() != null && !resume.getName().isBlank())
            score += 10;
        else
            missing.add("Name");

        if (resume.getEmail() != null && !resume.getEmail().isBlank())
            score += 10;
        else
            missing.add("Email");

        if (resume.getPhone() != null && !resume.getPhone().isBlank())
            score += 10;
        else
            missing.add("Phone");

        if (!resume.getEducation().isEmpty())
            score += 15;
        else
            missing.add("Education");

        if (!resume.getExperience().isEmpty())
            score += 15;
        else
            missing.add("Experience");

        if (!resume.getSkills().isEmpty())
            score += 20;
        else
            missing.add("Skills");

        if (!resume.getProjects().isEmpty())
            score += 10;
        else
            missing.add("Projects");

        if (!resume.getCertifications().isEmpty())
            score += 10;
        else
            missing.add("Certifications");

        return new ResumeScore(score, missing);
    }
}
package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillExtractor {

    private static final String[] SKILLS = {

            "Java",
            "Python",
            "Spring Boot",
            "Spring",
            "AWS",
            "Docker",
            "Kubernetes",
            "React",
            "Angular",
            "HTML",
            "CSS",
            "JavaScript",
            "TypeScript",
            "SQL",
            "MySQL",
            "PostgreSQL",
            "MongoDB",
            "Git",
            "GitHub",
            "REST API",
            "Hibernate",
            "JPA",
            "Flask",
            "FastAPI",
            "C",
            "C++",
            "Machine Learning",
            "Deep Learning",
            "TensorFlow",
            "PyTorch"
    };

    public List<String> extract(String text) {

        List<String> skills = new ArrayList<>();

        String lowerText = text.toLowerCase();

        for (String skill : SKILLS) {

            if (lowerText.contains(skill.toLowerCase())) {
                skills.add(skill);
            }
        }

        return skills;
    }
}
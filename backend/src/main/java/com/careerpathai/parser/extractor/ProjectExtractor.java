package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProjectExtractor {

    private static final Pattern PROJECT_PATTERN =
            Pattern.compile(
                    "(?is)(projects|academic projects|personal projects)(.*?)(experience|education|certifications|skills|$)"
            );

    public List<String> extract(String text) {

        List<String> projects = new ArrayList<>();

        Matcher matcher = PROJECT_PATTERN.matcher(text);

        if (matcher.find()) {

            String section = matcher.group(2);

            String[] lines = section.split("\\r?\\n");

            for (String line : lines) {

                line = line.trim();

                if (!line.isEmpty() && line.length() > 3) {
                    projects.add(line);
                }
            }
        }

        return projects;
    }
}
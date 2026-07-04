package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ExperienceExtractor {

    private static final Pattern EXPERIENCE_SECTION =
            Pattern.compile(
                    "(?is)(experience|work experience|professional experience|internship)(.*?)(education|projects|certifications|skills|$)"
            );

    public List<String> extract(String text) {

        List<String> experiences = new ArrayList<>();

        Matcher matcher = EXPERIENCE_SECTION.matcher(text);

        if (matcher.find()) {

            String section = matcher.group(2);

            String[] lines = section.split("\\r?\\n");

            for (String line : lines) {

                line = line.trim();

                if (!line.isEmpty() && line.length() > 3) {
                    experiences.add(line);
                }
            }
        }

        return experiences;
    }
}
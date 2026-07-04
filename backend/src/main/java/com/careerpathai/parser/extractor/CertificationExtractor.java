package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CertificationExtractor {

    private static final Pattern CERT_PATTERN =
            Pattern.compile(
                    "(?is)(certifications|certificates|achievements)(.*?)(experience|education|projects|skills|$)"
            );

    public List<String> extract(String text) {

        List<String> certificates = new ArrayList<>();

        Matcher matcher = CERT_PATTERN.matcher(text);

        if (matcher.find()) {

            String section = matcher.group(2);

            String[] lines = section.split("\\r?\\n");

            for (String line : lines) {

                line = line.trim();

                if (!line.isEmpty() && line.length() > 3) {
                    certificates.add(line);
                }
            }
        }

        return certificates;
    }
}
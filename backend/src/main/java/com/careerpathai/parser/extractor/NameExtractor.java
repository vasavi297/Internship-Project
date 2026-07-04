package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

@Component
public class NameExtractor {

    public String extract(String text) {

        String[] lines = text.split("\\r?\\n");

        for (String line : lines) {

            line = line.trim();

            if (!line.isEmpty() && line.length() > 2) {
                return line;
            }
        }

        return "";
    }
}
package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailExtractor {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");

    public String extract(String text) {

        Matcher matcher = EMAIL_PATTERN.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }
}
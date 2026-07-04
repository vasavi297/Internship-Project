package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneExtractor {

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("(\\+91[-\\s]?)?[6-9]\\d{9}");

    public String extract(String text) {

        Matcher matcher = PHONE_PATTERN.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }
}
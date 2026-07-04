package com.careerpathai.parser.extractor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EducationExtractor {

    public List<String> extract(String text) {

        List<String> education = new ArrayList<>();

        String lower = text.toLowerCase();

        if (lower.contains("b.tech"))
            education.add("B.Tech");

        if (lower.contains("bachelor"))
            education.add("Bachelor");

        if (lower.contains("m.tech"))
            education.add("M.Tech");

        if (lower.contains("master"))
            education.add("Master");

        if (lower.contains("engineering"))
            education.add("Engineering");

        return education;
    }
}
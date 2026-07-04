package com.careerpathai.controller;

import com.careerpathai.parser.ParsedResume;
import com.careerpathai.parser.ResumeParserService;
import com.careerpathai.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/parser")
public class ParserController {

    private final ResumeParserService parserService;

    public ParserController(ResumeParserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping(value = "/text", consumes = "multipart/form-data")
    public ApiResponse<ParsedResume> parseResume(
            @RequestParam("file") MultipartFile multipartFile)
            throws Exception {

        File file = File.createTempFile(
                "resume-",
                multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);

        ParsedResume parsedResume = parserService.parseResume(file);

        // Delete temporary file
        file.delete();

        return new ApiResponse<>(
                true,
                "Resume Parsed Successfully",
                parsedResume
        );
    }
}
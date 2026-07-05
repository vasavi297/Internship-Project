package com.careerpathai.analysis;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/resume")
public class ResumeAnalysisController {

    private final ResumeAnalysisService analysisService;

    public ResumeAnalysisController(ResumeAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping(value = "/analyze", consumes = "multipart/form-data")
    public ResumeAnalysisResponse analyzeResume(
            @RequestParam("file") MultipartFile multipartFile)
            throws Exception {

        File file = File.createTempFile(
                "resume-",
                multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);

        return analysisService.analyze(file);
    }
}
package com.careerpathai.controller;

import com.careerpathai.aws.S3Service;
import com.careerpathai.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final S3Service s3Service;

    public ResumeController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ApiResponse<String> uploadResume(
        @RequestParam("file") MultipartFile multipartFile)
        throws Exception {

        File file = File.createTempFile("resume-", multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);

        String result = s3Service.uploadFile(file);

        file.delete();

        return new ApiResponse<>(
                true,
                "Resume Uploaded Successfully",
                result
        );
    }
}
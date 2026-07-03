package com.careerpathai.controller;

import com.careerpathai.aws.S3Service;
import com.careerpathai.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.careerpathai.aws.dynamodb.DynamoDbService;
import com.careerpathai.model.ResumeMetadata;
import java.util.List;
import java.io.File;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    private final S3Service s3Service;
    private final DynamoDbService dynamoDbService;

   public ResumeController(S3Service s3Service,DynamoDbService dynamoDbService) {

        this.s3Service = s3Service;
        this.dynamoDbService = dynamoDbService;
   }
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ApiResponse<String> uploadResume(
        @RequestParam("file") MultipartFile multipartFile)
        throws Exception {

        File file = File.createTempFile("resume-", multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);

      String s3Key = s3Service.uploadFile(file);

      ResumeMetadata metadata = new ResumeMetadata();

        metadata.setFileName(multipartFile.getOriginalFilename());
        metadata.setS3Key(s3Key);
        metadata.setFileSize(multipartFile.getSize());
        metadata.setStatus("Uploaded");

        dynamoDbService.saveResumeMetadata(metadata);

        file.delete();

        return new ApiResponse<>(
            true,
            "Resume Uploaded Successfully",
            metadata.getResumeId()
        );
    }
    @GetMapping("/all")
    public List<ResumeMetadata> getAllResumes() 
    {

    return dynamoDbService.getAllResumes();

    }
    @GetMapping("/{resumeId}")
    public ResumeMetadata getResumeById(
        @PathVariable String resumeId) {

    return dynamoDbService.getResumeById(resumeId);

    }  
    @DeleteMapping("/{resumeId}")
    public ApiResponse<String> deleteResume(
        @PathVariable String resumeId) {

    ResumeMetadata resume =
            dynamoDbService.getResumeById(resumeId);

    if (resume == null) {

        return new ApiResponse<>(
                false,
                "Resume Not Found",
                null
        );
    }

    s3Service.deleteFile(resume.getS3Key());

    dynamoDbService.deleteResume(resumeId);

    return new ApiResponse<>(
            true,
            "Resume Deleted Successfully",
            resumeId
    );

} 

}
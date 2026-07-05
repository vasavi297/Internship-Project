package com.careerpathai.analysis;

import com.careerpathai.aws.S3Service;
import com.careerpathai.aws.dynamodb.DynamoDbService;
import com.careerpathai.model.ResumeMetadata;
import com.careerpathai.model.ResumeStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/resume")
public class ResumeAnalysisController {

    private final ResumeAnalysisService analysisService;
    private final S3Service s3Service;
    private final DynamoDbService dynamoDbService;

    public ResumeAnalysisController(
            ResumeAnalysisService analysisService,
            S3Service s3Service,
            DynamoDbService dynamoDbService) {

        this.analysisService = analysisService;
        this.s3Service = s3Service;
        this.dynamoDbService = dynamoDbService;
    }

    @PostMapping(value = "/analyze", consumes = "multipart/form-data")
    public ResumeAnalysisResponse analyzeResume(
            @RequestParam("file") MultipartFile multipartFile)
            throws Exception {

        File file = File.createTempFile(
                "resume-",
                multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);

        ResumeMetadata metadata = new ResumeMetadata();

        try {

            // Upload to S3
            String s3Key = s3Service.uploadFile(file);

            metadata.setFileName(multipartFile.getOriginalFilename());
            metadata.setFileSize(multipartFile.getSize());
            metadata.setS3Key(s3Key);
            metadata.setStatus(ResumeStatus.UPLOADED.name());

            // Save initial metadata
            dynamoDbService.saveResumeMetadata(metadata);

            // Update status
            metadata.setStatus(ResumeStatus.ANALYZING.name());
            dynamoDbService.saveResumeMetadata(metadata);

            // Analyze Resume
            ResumeAnalysisResponse response =
                    analysisService.analyze(file);

            // Update metadata with analysis result
            metadata.setCandidateName(
                    response.getParsedResume().getName());

            metadata.setEmail(
                    response.getParsedResume().getEmail());

            if (response.getRecommendedCareer() != null) {

                metadata.setRecommendedCareer(
                        response.getRecommendedCareer()
                                .getCareerName());
            }

            if (response.getResumeScore() != null) {

                metadata.setResumeScore(
                        response.getResumeScore()
                                .getOverallScore());
            }

            metadata.setStatus(
                    ResumeStatus.ANALYZED.name());

            dynamoDbService.saveResumeMetadata(metadata);

            // Return Resume Id also
            response.setResumeId(
                    metadata.getResumeId());

            return response;

        } catch (Exception ex) {

            metadata.setStatus(
                    ResumeStatus.FAILED.name());

            dynamoDbService.saveResumeMetadata(metadata);

            throw ex;

        } finally {

            if (file.exists()) {
                file.delete();
            }
        }
    }
}
package com.careerpathai.analysis;

import com.careerpathai.auth.JwtTokenService;
import com.careerpathai.aws.s3.S3Service;
import com.careerpathai.aws.dynamodb.DynamoDbService;
import com.careerpathai.model.ResumeMetadata;
import com.careerpathai.model.ResumeStatus;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeAnalysisController {

    private final ResumeAnalysisService analysisService;

    private final S3Service s3Service;

    private final DynamoDbService dynamoDbService;

    private final JwtTokenService jwtTokenService;

    public ResumeAnalysisController(
            ResumeAnalysisService analysisService,
            S3Service s3Service,
            JwtTokenService jwtTokenService,
            DynamoDbService dynamoDbService) {

        this.analysisService = analysisService;
        this.s3Service = s3Service;
        this.jwtTokenService = jwtTokenService;
        this.dynamoDbService = dynamoDbService;
    }

    @PostMapping(value = "/analyze", consumes = "multipart/form-data")
    public ResumeAnalysisResponse analyzeResume(
            JwtAuthenticationToken authentication,
            @RequestParam("file") MultipartFile multipartFile)
            throws Exception {

        File file = File.createTempFile(
                "resume-",
                multipartFile.getOriginalFilename());

        multipartFile.transferTo(file);

        ResumeMetadata metadata = new ResumeMetadata();

        metadata.setUserId(
                jwtTokenService.getUserId(authentication));

        try {

            // Upload Resume

            String s3Key =
                    s3Service.uploadFile(file);

            metadata.setFileName(
                    multipartFile.getOriginalFilename());

            metadata.setFileSize(
                    multipartFile.getSize());

            metadata.setS3Key(s3Key);

            metadata.setStatus(
                    ResumeStatus.UPLOADED.name());

            dynamoDbService.saveResumeMetadata(metadata);

            // Update Status

            metadata.setStatus(
                    ResumeStatus.ANALYZING.name());

            dynamoDbService.saveResumeMetadata(metadata);

            // Analyze Resume

            ResumeAnalysisResponse response =
                    analysisService.analyze(file);

            // Save Candidate Details

            if (response.getParsedResume() != null) {

                metadata.setCandidateName(
                        response.getParsedResume().getName());

                metadata.setEmail(
                        response.getParsedResume().getEmail());

            }

            // Save Career

            if (response.getRecommendedCareer() != null) {

    metadata.setRecommendedCareer(
            response.getRecommendedCareer()
                    .getCareerName());

    metadata.setCareerMatch(
            response.getRecommendedCareer()
                    .getMatchPercentage());

}

            // Save Resume Score

            if (response.getResumeScore() != null) {

                metadata.setResumeScore(
                        response.getResumeScore()
                                .getOverallScore());

            }

            // Save Missing Skill Count

            metadata.setMissingSkillCount(
                    response.getTopMissingSkills().size());

            metadata.setStatus(
                    ResumeStatus.ANALYZED.name());

            dynamoDbService.saveResumeMetadata(metadata);

            response.setResumeId(
                    metadata.getResumeId());

            return response;

        } catch (Exception exception) {

            metadata.setStatus(
                    ResumeStatus.FAILED.name());

            dynamoDbService.saveResumeMetadata(metadata);

            throw exception;

        } finally {

            if (file.exists()) {

                file.delete();

            }

        }

    }

    @GetMapping("/all")
    public List<ResumeMetadata> getAllResumes(
            JwtAuthenticationToken authentication) {

        String userId =
                jwtTokenService.getUserId(authentication);

        return dynamoDbService.getResumesByUser(userId);

    }

    @GetMapping("/{resumeId}")
    public ResumeAnalysisResponse getResume(
            JwtAuthenticationToken authentication,
            @PathVariable String resumeId)
            throws Exception {

        String userId =
                jwtTokenService.getUserId(authentication);

        ResumeMetadata metadata =
                dynamoDbService.getResumeById(resumeId);

        if (metadata == null) {

            throw new RuntimeException(
                    "Resume not found.");

        }

        if (!userId.equals(metadata.getUserId())) {

            throw new RuntimeException(
                    "Access Denied.");

        }

        File resumeFile =
                s3Service.downloadFile(
                        metadata.getS3Key());

        try {

            ResumeAnalysisResponse response =
                    analysisService.analyze(
                            resumeFile);

            response.setResumeId(
                    metadata.getResumeId());

            return response;

        } finally {

            if (resumeFile.exists()) {

                resumeFile.delete();

            }

        }

    }

    @DeleteMapping("/{resumeId}")
    public void deleteResume(
            JwtAuthenticationToken authentication,
            @PathVariable String resumeId) {

        String userId =
                jwtTokenService.getUserId(authentication);

        ResumeMetadata metadata =
                dynamoDbService.getResumeById(resumeId);

        if (metadata == null) {

            return;

        }

        if (!userId.equals(metadata.getUserId())) {

            throw new RuntimeException(
                    "Access Denied.");

        }

        if (metadata.getS3Key() != null) {

            s3Service.deleteFile(
                    metadata.getS3Key());

        }

        dynamoDbService.deleteResume(
                resumeId);

    }

}
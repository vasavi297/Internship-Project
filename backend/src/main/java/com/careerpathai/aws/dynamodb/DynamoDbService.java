package com.careerpathai.aws.dynamodb;

import com.careerpathai.model.ResumeMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DynamoDbService {

    private final DynamoDbClient dynamoDbClient;

    @Value("${aws.dynamodb.table}")
    private String tableName;

    public DynamoDbService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    // ===================================================
    // Save Resume Metadata
    // ===================================================

    public void saveResumeMetadata(ResumeMetadata resume) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put("resumeId",
                AttributeValue.builder().s(resume.getResumeId()).build());

        item.put("userId",
                AttributeValue.builder().s(resume.getUserId()).build());

        item.put("fileName",
                AttributeValue.builder().s(resume.getFileName()).build());

        item.put("s3Key",
                AttributeValue.builder().s(resume.getS3Key()).build());

        item.put("status",
                AttributeValue.builder().s(resume.getStatus()).build());

        item.put("fileSize",
                AttributeValue.builder()
                        .n(String.valueOf(resume.getFileSize()))
                        .build());

        item.put("uploadTime",
                AttributeValue.builder()
                        .s(resume.getUploadTime().toString())
                        .build());

        if (resume.getAnalyzedTime() != null) {

            item.put("analyzedTime",
                    AttributeValue.builder()
                            .s(resume.getAnalyzedTime().toString())
                            .build());

        }

        if (resume.getCandidateName() != null) {

            item.put("candidateName",
                    AttributeValue.builder()
                            .s(resume.getCandidateName())
                            .build());

        }

        if (resume.getEmail() != null) {

            item.put("email",
                    AttributeValue.builder()
                            .s(resume.getEmail())
                            .build());

        }

        if (resume.getRecommendedCareer() != null) {

            item.put("recommendedCareer",
                    AttributeValue.builder()
                            .s(resume.getRecommendedCareer())
                            .build());

        }

        if (resume.getResumeScore() != null) {

            item.put("resumeScore",
                    AttributeValue.builder()
                            .n(String.valueOf(resume.getResumeScore()))
                            .build());

        }

        if (resume.getCareerMatch() != null) {

            item.put("careerMatch",
                    AttributeValue.builder()
                            .n(String.valueOf(resume.getCareerMatch()))
                            .build());

        }

        if (resume.getMissingSkillCount() != null) {

            item.put("missingSkillCount",
                    AttributeValue.builder()
                            .n(String.valueOf(resume.getMissingSkillCount()))
                            .build());

        }

        PutItemRequest request =
                PutItemRequest.builder()
                        .tableName(tableName)
                        .item(item)
                        .build();

        dynamoDbClient.putItem(request);

    }

    // ===================================================
    // Get All Resumes Of Logged-in User
    // ===================================================

    public List<ResumeMetadata> getResumesByUser(String userId) {

        QueryRequest request =
                QueryRequest.builder()
                        .tableName(tableName)
                        .indexName("UserIdIndex")
                        .keyConditionExpression("userId = :uid")
                        .expressionAttributeValues(
                                Map.of(
                                        ":uid",
                                        AttributeValue.builder()
                                                .s(userId)
                                                .build()
                                )
                        )
                        .build();

        QueryResponse response =
                dynamoDbClient.query(request);

        List<ResumeMetadata> resumes =
                new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {

            resumes.add(mapItem(item));

        }

        resumes.sort((a, b) ->
                b.getUploadTime().compareTo(a.getUploadTime()));

        return resumes;

    }

    // ===================================================
    // Get Resume By Resume Id
    // ===================================================

    public ResumeMetadata getResumeById(String resumeId) {

        GetItemRequest request =
                GetItemRequest.builder()
                        .tableName(tableName)
                        .key(
                                Map.of(
                                        "resumeId",
                                        AttributeValue.builder()
                                                .s(resumeId)
                                                .build()
                                )
                        )
                        .build();

        GetItemResponse response =
                dynamoDbClient.getItem(request);

        if (!response.hasItem()) {

            return null;

        }

        return mapItem(response.item());

    }

    // ===================================================
    // Delete Resume
    // ===================================================

    public void deleteResume(String resumeId) {

        DeleteItemRequest request =
                DeleteItemRequest.builder()
                        .tableName(tableName)
                        .key(
                                Map.of(
                                        "resumeId",
                                        AttributeValue.builder()
                                                .s(resumeId)
                                                .build()
                                )
                        )
                        .build();

        dynamoDbClient.deleteItem(request);

    }

    // ===================================================
    // DynamoDB Item -> ResumeMetadata
    // ===================================================

    private ResumeMetadata mapItem(
            Map<String, AttributeValue> item) {

        ResumeMetadata resume =
                new ResumeMetadata();

        resume.setResumeId(item.get("resumeId").s());

        if (item.containsKey("userId")) {

            resume.setUserId(item.get("userId").s());

        }

        resume.setFileName(item.get("fileName").s());

        resume.setS3Key(item.get("s3Key").s());

        resume.setStatus(item.get("status").s());

        resume.setFileSize(
                Long.parseLong(item.get("fileSize").n()));

        resume.setUploadTime(
                LocalDateTime.parse(item.get("uploadTime").s()));

        if (item.containsKey("analyzedTime")) {

            resume.setAnalyzedTime(
                    LocalDateTime.parse(item.get("analyzedTime").s()));

        }

        if (item.containsKey("candidateName")) {

            resume.setCandidateName(
                    item.get("candidateName").s());

        }

        if (item.containsKey("email")) {

            resume.setEmail(
                    item.get("email").s());

        }

        if (item.containsKey("recommendedCareer")) {

            resume.setRecommendedCareer(
                    item.get("recommendedCareer").s());

        }

        if (item.containsKey("resumeScore")) {

            resume.setResumeScore(
                    Integer.parseInt(item.get("resumeScore").n()));

        }

        if (item.containsKey("careerMatch")) {

            resume.setCareerMatch(
                    Double.parseDouble(item.get("careerMatch").n()));

        }

        if (item.containsKey("missingSkillCount")) {

            resume.setMissingSkillCount(
                    Integer.parseInt(item.get("missingSkillCount").n()));

        }

        return resume;

    }

}
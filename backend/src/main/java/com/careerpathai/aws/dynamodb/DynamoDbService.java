package com.careerpathai.aws.dynamodb;

import com.careerpathai.model.ResumeMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Service
public class DynamoDbService {

    private final DynamoDbClient dynamoDbClient;

    @Value("${aws.dynamodb.table}")
    private String tableName;

    public DynamoDbService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void saveResumeMetadata(ResumeMetadata resume) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put("resumeId", AttributeValue.builder().s(resume.getResumeId()).build());
        item.put("fileName", AttributeValue.builder().s(resume.getFileName()).build());
        item.put("s3Key", AttributeValue.builder().s(resume.getS3Key()).build());
        item.put("status", AttributeValue.builder().s(resume.getStatus()).build());
        item.put("fileSize", AttributeValue.builder().n(String.valueOf(resume.getFileSize())).build());
        item.put("uploadTime", AttributeValue.builder().s(resume.getUploadTime().toString()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
    }
    public List<ResumeMetadata> getAllResumes() {

    ScanRequest request = ScanRequest.builder()
            .tableName(tableName)
            .build();

    ScanResponse response = dynamoDbClient.scan(request);

    List<ResumeMetadata> resumes = new ArrayList<>();

    for (Map<String, AttributeValue> item : response.items()) {

        ResumeMetadata resume = new ResumeMetadata();

        resume.setResumeId(item.get("resumeId").s());
        resume.setFileName(item.get("fileName").s());
        resume.setS3Key(item.get("s3Key").s());
        resume.setStatus(item.get("status").s());
        resume.setFileSize(Long.parseLong(item.get("fileSize").n()));
        resume.setUploadTime(LocalDateTime.parse(item.get("uploadTime").s()));

        resumes.add(resume);
    }

    return resumes;
    }
    public ResumeMetadata getResumeById(String resumeId) {

    Map<String, AttributeValue> key = new HashMap<>();

    key.put("resumeId", AttributeValue.builder().s(resumeId).build());

    GetItemRequest request = GetItemRequest.builder()
            .tableName(tableName)
            .key(key)
            .build();

    GetItemResponse response = dynamoDbClient.getItem(request);

    if (!response.hasItem()) {
        return null;
    }

    Map<String, AttributeValue> item = response.item();

    ResumeMetadata resume = new ResumeMetadata();

    resume.setResumeId(item.get("resumeId").s());
    resume.setFileName(item.get("fileName").s());
    resume.setS3Key(item.get("s3Key").s());
    resume.setStatus(item.get("status").s());
    resume.setFileSize(Long.parseLong(item.get("fileSize").n()));
    resume.setUploadTime(java.time.LocalDateTime.parse(item.get("uploadTime").s()));

    return resume;
    }
    public void deleteResume(String resumeId) 
    {

    Map<String, AttributeValue> key = new HashMap<>();

    key.put("resumeId",
            AttributeValue.builder().s(resumeId).build());

    DeleteItemRequest request = DeleteItemRequest.builder()
            .tableName(tableName)
            .key(key)
            .build();

    dynamoDbClient.deleteItem(request);

    }

}
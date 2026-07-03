package com.careerpathai.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import java.io.File;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(File file) {

    String key = file.getName();

    PutObjectRequest request = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

    s3Client.putObject(request, RequestBody.fromFile(file));

    return key;
    }
    public void deleteFile(String key) 
    {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

    s3Client.deleteObject(request);

    }

}
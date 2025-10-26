package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.AwsProperties;
import com.example.ecommerce.service.S3Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;
    private final AwsProperties awsProperties;


    public S3ServiceImpl(S3Client s3Client, AwsProperties awsProperties) {
        this.s3Client = s3Client;
        this.awsProperties = awsProperties;
    }

    @Override
    public String uploadFile(MultipartFile file, String filePath, String bucketPath) {
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.getS3Bucket())
                    .key(bucketPath + filePath)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            return "https://" + awsProperties.getS3Bucket() + ".s3." +
                    awsProperties.getRegion() + ".amazonaws.com/" + filePath;
        }catch (IOException e){
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    @Override
    public void deleteFile(String fileKey) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(awsProperties.getS3Bucket())
                .key(fileKey)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(String fileKey) {
        try {
            System.out.println("HEllooooo"+ awsProperties.getS3Bucket());
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(awsProperties.getS3Bucket())
                    .key(fileKey)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes =
                    s3Client.getObjectAsBytes(getObjectRequest);

            byte[] data = objectBytes.asByteArray();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileKey + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(data);

        } catch (Exception e) {
            throw new RuntimeException("Failed to download file from S3: " + fileKey, e);
        }
    }
}

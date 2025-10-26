package com.example.ecommerce.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile file, String filePath, String bucketPath);

    public void deleteFile(String fileKey);

    public ResponseEntity<byte[]> downloadFile(String fileKey);
}

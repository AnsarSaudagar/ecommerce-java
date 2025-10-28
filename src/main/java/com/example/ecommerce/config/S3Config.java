package com.example.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@ConditionalOnProperty(name = "aws.access-key")
public class S3Config {

    @Autowired
    private AwsProperties awsProperties;

    @Bean
    public S3Client s3Client(){
        try {
            AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                    awsProperties.getAccessKey(),
                    awsProperties.getSecretKey()
            );

            return S3Client.builder()
                    .region(Region.of(awsProperties.getRegion()))
                    .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize S3 client", e);
        }
    }
}

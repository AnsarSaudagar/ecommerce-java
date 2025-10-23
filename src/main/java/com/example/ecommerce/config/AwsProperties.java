package com.example.ecommerce.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "aws")
public class AwsProperties {
    private String region;
    private String s3Bucket;
    private String accessKey;
    private String secretKey;
}

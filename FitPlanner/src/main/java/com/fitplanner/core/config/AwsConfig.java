package com.fitplanner.core.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * package     : com.fitplanner.core.config
 * file        : AwsConfig
 * author      : choeuiseung
 * date        : 2022/10/29
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/29                choeuiseung                 최초 생성
 * =======================================================
 */
@Configuration
@RequiredArgsConstructor
public class AwsConfig {

    private final Environment env;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean("basicAWSCredentials")
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(basicAWSCredentials());
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(awsStaticCredentialsProvider)
                .withRegion(String.valueOf(Region.getRegion(Regions.fromName(region))))
                .build();
    }


}

package com.stack.knowledge.thirdparty.aws.s3.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.stack.knowledge.thirdparty.aws.AwsProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsS3Config(
    private val awsProperties: AwsProperties
) {
    @Value("\${cloud.aws.region.static}")
    lateinit var region: String

    @Bean
    fun amazonS3(): AmazonS3 {
        val awsCredentials = BasicAWSCredentials(awsProperties.accessKey, awsProperties.secretKey)
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }
}
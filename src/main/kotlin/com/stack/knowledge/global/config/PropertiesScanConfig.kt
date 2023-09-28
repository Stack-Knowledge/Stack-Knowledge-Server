package com.stack.knowledge.global.config

import com.stack.knowledge.global.security.token.properties.JwtProperties
import com.stack.knowledge.thirdparty.aws.AwsProperties
import com.stack.knowledge.thirdparty.aws.s3.config.properties.AwsS3Properties
import com.stack.knowledge.thirdparty.gauth.properties.GAuthProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        GAuthProperties::class,
        AwsProperties::class,
        AwsS3Properties::class
    ]
)
class PropertiesScanConfig
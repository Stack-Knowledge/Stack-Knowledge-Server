package com.stack.knowledege.global.config

import com.stack.knowledege.global.security.token.properties.JwtProperties
import com.stack.knowledege.thirdparty.aws.AwsProperties
import com.stack.knowledege.thirdparty.gauth.properties.GAuthProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        GAuthProperties::class,
        AwsProperties::class
    ]
)
class PropertiesScanConfig
package com.stack.knowledge.global.config

import com.stack.knowledge.global.security.token.properties.JwtProperties
import com.stack.knowledge.thirdparty.aws.AwsProperties
import com.stack.knowledge.thirdparty.gauth.properties.GAuthProperties
import com.stack.knowledge.thirdparty.fcm.property.FcmProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        GAuthProperties::class,
        AwsProperties::class,
        FcmProperties::class
    ]
)
class PropertiesScanConfig
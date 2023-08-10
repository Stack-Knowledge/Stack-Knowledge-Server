package com.stack.knowledege.global.config

import com.stack.knowledege.global.security.token.properties.JwtProperties
import com.stack.knowledege.thirdparty.gauth.GAuthProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        GAuthProperties::class
    ]
)
class PropertiesScanConfig
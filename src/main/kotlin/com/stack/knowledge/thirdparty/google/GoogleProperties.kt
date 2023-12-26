package com.stack.knowledge.thirdparty.google

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "auth.google")
@ConstructorBinding
class GoogleProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUrl: String
)
package com.stack.knowledege.thirdparty.gauth.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "gauth")
class GAuthProperties(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)
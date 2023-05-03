package com.stack.knowledege.global.security.jwt.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.nio.charset.StandardCharsets
import java.security.Key

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    accessToken: String,
    refreshToken: String
) {
    val accessSecret: Key
    val refreshSecret: Key

    init {
        this.accessSecret = Keys.hmacShaKeyFor(accessToken.toByteArray(StandardCharsets.UTF_8))
        this.refreshSecret = Keys.hmacShaKeyFor(refreshToken.toByteArray(StandardCharsets.UTF_8))
    }
}
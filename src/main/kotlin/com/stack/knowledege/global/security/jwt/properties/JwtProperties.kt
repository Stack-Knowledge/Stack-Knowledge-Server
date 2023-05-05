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
    refreshToken: String,
    accessExp: Int,
    refreshExp: Int
) {

    val accessSecret: Key = Keys.hmacShaKeyFor(accessToken.toByteArray(StandardCharsets.UTF_8))
    val refreshSecret: Key = Keys.hmacShaKeyFor(refreshToken.toByteArray(StandardCharsets.UTF_8))

    companion object {
        const val accessType = "access"
        const val refreshType = "refresh"
        const val tokenPrefix = "Bearer "
        const val tokenHeader = "Authorization"
    }
}
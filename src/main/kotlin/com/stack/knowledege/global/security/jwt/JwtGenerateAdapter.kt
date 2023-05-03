package com.stack.knowledege.global.security.jwt

import com.stack.knowledege.domain.user.adapter.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.user.application.spi.JwtPort
import com.stack.knowledege.global.security.jwt.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.security.Key
import java.time.ZonedDateTime
import java.util.*

@Component
class JwtGenerateAdapter(
    private val jwtProperties: JwtProperties,
): JwtPort {

    companion object {
        const val ACCESS_TYPE = "access"
        const val REFRESH_TYPE = "refresh"
        const val ACCESS_EXP = 60L * 15
        const val REFRESH_EXP = 60L * 60 * 24 * 7
    }

    override fun receiveToken(email: String): TokenResponse {
        return TokenResponse(
            accessToken = generateAccessToken(email),
            refreshToken = generateRefreshToken(email),
            expiredAt = getExpiredAtToken
        )
    }

    private fun generateAccessToken(email: String): String {
        return generateToken(email, ACCESS_TYPE, jwtProperties.accessSecret, ACCESS_EXP)
    }

    private fun generateRefreshToken(email: String): String {
        return generateToken(email, REFRESH_TYPE, jwtProperties.refreshSecret, REFRESH_EXP)
    }

    val getExpiredAtToken: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(ACCESS_EXP)

    private fun generateToken(sub: String, type: String, secret: Key, exp: Long): String {
        return Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(sub)
            .claim("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
    }
}
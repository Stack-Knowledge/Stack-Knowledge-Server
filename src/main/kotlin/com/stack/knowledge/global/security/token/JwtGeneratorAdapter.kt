package com.stack.knowledge.global.security.token

import com.stack.knowledge.domain.auth.application.spi.CommandRefreshTokenPort
import com.stack.knowledge.domain.auth.domain.RefreshToken
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import com.stack.knowledge.global.security.token.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class JwtGeneratorAdapter(
    private val jwtProperties: JwtProperties,
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : JwtGeneratorPort {

    override fun receiveToken(userId: UUID, authority: Authority): TokenResponse {
        val refreshToken = generateRefreshToken(userId)
        commandRefreshTokenPort.save(RefreshToken(refreshToken, userId, authority, jwtProperties.refreshExp))

        return TokenResponse(
            accessToken = generateAccessToken(userId, authority),
            refreshToken = refreshToken,
            expiredAt = getAccessTokenExpiredAt(),
            authority = authority
        )
    }

    private fun generateAccessToken(userId: UUID, authority: Authority): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", JwtProperties.accessType)
            .claim("authority", authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()


    private fun generateRefreshToken(userId: UUID): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", JwtProperties.refreshType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()


    private fun getAccessTokenExpiredAt(): ZonedDateTime =
        ZonedDateTime.now().plusSeconds(jwtProperties.accessExp.toLong())
}
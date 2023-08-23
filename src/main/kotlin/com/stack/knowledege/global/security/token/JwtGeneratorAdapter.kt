package com.stack.knowledege.global.security.token

import com.stack.knowledege.domain.auth.application.spi.CommandRefreshTokenPort
import com.stack.knowledege.domain.auth.domain.RefreshToken
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.global.security.token.properties.JwtProperties
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

    override fun receiveToken(userId: UUID, authority: String): TokenResponse {
        val refreshToken = generateRefreshToken(userId)
        commandRefreshTokenPort.saveRefreshToken(RefreshToken(refreshToken, userId, jwtProperties.refreshExp))
        return TokenResponse(
            accessToken = generateAccessToken(userId, authority),
            refreshToken = refreshToken,
            expiredAt = getAccessTokenExpiredAt()
        )
    }

    private fun generateAccessToken(userId: UUID, authority: String): String =
        Jwts.builder()
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", "access")
            .claim("authority", authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()


    private fun generateRefreshToken(userId: UUID): String =
        Jwts.builder()
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", "refresh")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()


    private fun getAccessTokenExpiredAt(): ZonedDateTime =
        ZonedDateTime.now().plusSeconds(jwtProperties.accessExp.toLong())
}
package com.stack.knowledege.global.security.token

import com.stack.knowledege.domain.auth.application.spi.CommandRefreshTokenPort
import com.stack.knowledege.domain.auth.domain.RefreshToken
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.global.security.token.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.security.Key
import java.time.ZonedDateTime
import java.util.*

@Component
class JwtGeneratorAdapter(
    private val jwtProperties: JwtProperties,
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : JwtGeneratorPort {

    companion object {
        const val ACCESS_TYPE = "access"
        const val REFRESH_TYPE = "refresh"
        const val ACCESS_EXP = 60L * 30
        const val REFRESH_EXP = 60L * 60 * 24 * 7
    }

    override fun receiveToken(email: String): TokenResponse {
        val refreshToken = generateRefreshToken(email)
        commandRefreshTokenPort.saveRefreshToken(RefreshToken(email, refreshToken, jwtProperties.refreshExp))
        return TokenResponse(
            accessToken = generateAccessToken(email),
            refreshToken = refreshToken,
            expiredAt = getExpiredAtToken
        )
    }

    private fun generateAccessToken(email: String): String =
        generateToken(email, ACCESS_TYPE, jwtProperties.accessSecret, ACCESS_EXP)


    private fun generateRefreshToken(email: String): String =
        generateToken(email, REFRESH_TYPE, jwtProperties.refreshSecret, REFRESH_EXP)


    val getExpiredAtToken: ZonedDateTime
        get() = ZonedDateTime.now().plusSeconds(ACCESS_EXP)

    private fun generateToken(sub: String, type: String, secret: Key, exp: Long): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(sub)
            .claim("type", type)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .compact()
}
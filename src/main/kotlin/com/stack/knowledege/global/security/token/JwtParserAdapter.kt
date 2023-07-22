package com.stack.knowledege.global.security.token

import com.stack.knowledege.global.error.exception.InternalServerError
import com.stack.knowledege.global.security.exception.ExpiredTokenException
import com.stack.knowledege.global.security.exception.InvalidTokenException
import com.stack.knowledege.global.security.principal.AuthDetailsService
import com.stack.knowledege.global.security.token.properties.JwtProperties
import com.stack.knowledege.global.security.spi.JwtParserPort
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import javax.servlet.http.HttpServletRequest

@Component
class JwtParserAdapter(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService
) : JwtParserPort {
    override fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.tokenHeader)
            .let { it ?: return null }
            .let { if (it.startsWith(JwtProperties.tokenPrefix)) it.replace(JwtProperties.tokenPrefix, "") else null }

    override fun parseRefreshToken(refreshToken: String): String? =
        if (refreshToken.startsWith(JwtProperties.tokenPrefix)) refreshToken.replace(JwtProperties.tokenPrefix, "") else null

    override fun authentication(accessToken: String): Authentication =
        authDetailsService.loadUserByUsername(getTokenBody(accessToken, jwtProperties.accessSecret).subject)
            .let { UsernamePasswordAuthenticationToken(it, "", it.authorities) }

    private fun getTokenBody(token: String, secret: Key): Claims =
        try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> throw ExpiredTokenException()
                is InvalidClaimException -> throw InvalidTokenException()
                is JwtException -> throw InvalidTokenException()
                else -> throw InternalServerError()
            }
        }
}
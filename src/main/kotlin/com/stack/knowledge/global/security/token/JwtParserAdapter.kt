package com.stack.knowledge.global.security.token

import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.global.error.exception.InternalServerError
import com.stack.knowledge.global.security.exception.ExpiredTokenException
import com.stack.knowledge.global.security.exception.InvalidTokenException
import com.stack.knowledge.global.security.principal.StudentDetailsService
import com.stack.knowledge.global.security.principal.TeacherDetailsService
import com.stack.knowledge.global.security.token.properties.JwtProperties
import com.stack.knowledge.global.security.spi.JwtParserPort
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import javax.servlet.http.HttpServletRequest

@Component
class JwtParserAdapter(
    private val jwtProperties: JwtProperties,
    private val teacherDetailsService: TeacherDetailsService,
    private val studentDetailsService: StudentDetailsService
) : JwtParserPort {
    override fun parseAccessToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.tokenHeader)
            .let { it ?: return null }
            .let { if (it.startsWith(JwtProperties.tokenPrefix))
                it.replace(JwtProperties.tokenPrefix, "")
            else null }

    override fun parseRefreshToken(refreshToken: String): String? =
        if (refreshToken.startsWith(JwtProperties.tokenPrefix))
            refreshToken.replace(JwtProperties.tokenPrefix, "")
        else null

    override fun authentication(accessToken: String): Authentication =
        getAuthority(getTokenBody(accessToken, jwtProperties.accessSecret))
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

    private fun getAuthority(body: Claims): UserDetails =
        when (body.get(JwtProperties.authority, String::class.java)) {
            Authority.ROLE_TEACHER.name -> teacherDetailsService.loadUserByUsername(body.subject)
            Authority.ROLE_STUDENT.name -> studentDetailsService.loadUserByUsername(body.subject)
            else -> throw InvalidTokenException()
        }
}
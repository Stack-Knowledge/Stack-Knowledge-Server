package com.stack.knowledge.global.security.handler

import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import com.stack.knowledge.thirdparty.google.GoogleProperties
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class CustomLoginSuccessHandler(
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort,
    private val googleProperties: GoogleProperties
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        println("===========================")
        val userDetails: UserDetails = authentication.principal as UserDetails
        val email = userDetails.username

        val user = userPort.queryUserByEmail(email) ?: throw UserNotFoundException()

        val redirectUrlWithParameter = UriComponentsBuilder
            .fromUriString(getTargetUrl(user.authority))
            .build()
            .toUriString()

        val tokenResponse = jwtGeneratorPort.generateToken(user.id, user.authority)


        response.status = HttpServletResponse.SC_OK
        response.contentType = "application/json"
        response.writer.write("""
            {
                "accessToken": "${tokenResponse.accessToken}",
                "refreshToken": "${tokenResponse.refreshToken}",
                "expiredAt": ${tokenResponse.expiredAt}",
                "authority": ${tokenResponse.authority}"
            }
        """.trimIndent())
        response.sendRedirect(redirectUrlWithParameter)
    }

    private fun getTargetUrl(authority: Authority): String =
        when (authority) {
            Authority.ROLE_STUDENT -> googleProperties.redirectStudentUrl
            Authority.ROLE_TEACHER -> googleProperties.redirectTeacherUrl
        }
}
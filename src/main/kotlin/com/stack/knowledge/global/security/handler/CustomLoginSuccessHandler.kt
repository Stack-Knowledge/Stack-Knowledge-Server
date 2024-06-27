package com.stack.knowledge.global.security.handler

import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomLoginSuccessHandler(
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val userDetails: UserDetails = authentication.principal as UserDetails
        val email = userDetails.username

        val user = userPort.queryUserByEmail(email) ?: throw UserNotFoundException()

        val redirectUrlWithParameter = UriComponentsBuilder
            .fromUriString(getTargetUrl(user.authority))
            .build()
            .toUriString()
        response.sendRedirect(redirectUrlWithParameter)

        val tokenResponse = jwtGeneratorPort.generateToken(user.id, user.authority)

        response.status = HttpServletResponse.SC_OK
        response.setHeader("Authorization", "Bearer ${tokenResponse.accessToken}")
        response.setHeader("RefreshToken", tokenResponse.refreshToken)
    }

    private fun getTargetUrl(authority: Authority): String =
        when (authority) {
            Authority.ROLE_STUDENT -> REDIRECT_STUDENT_URL
            Authority.ROLE_TEACHER -> REDIRECT_TEACHER_URL
        }

    companion object {
        const val REDIRECT_STUDENT_URL = "adf"
        const val REDIRECT_TEACHER_URL = "ASdf"
    }
}
package com.stack.knowledge.global.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomLoginFailureHandler : AuthenticationFailureHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        response.status = HttpServletResponse.SC_BAD_REQUEST

        log.error("로그인 실패 : ${exception.stackTrace}")
    }
}
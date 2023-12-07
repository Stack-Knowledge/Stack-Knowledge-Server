package com.stack.knowledge.global.filter

import com.stack.knowledge.global.security.spi.JwtParserPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtRequestFilter(
    private val jwtParserPort: JwtParserPort
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = jwtParserPort.parseAccessToken(request)

        if (!accessToken.isNullOrBlank()) {
            val authentication = jwtParserPort.authentication(accessToken)
            SecurityContextHolder.clearContext()
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}
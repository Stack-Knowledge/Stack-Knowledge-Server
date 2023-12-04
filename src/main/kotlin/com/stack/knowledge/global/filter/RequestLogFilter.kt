package com.stack.knowledge.global.filter

import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestLogFilter: OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("client ip = ${request.remoteAddr}")
        log.info("request method = ${request.method}")
        log.info("request url = ${request.requestURI}")

        filterChain.doFilter(request, response)

        log.info("response status = ${response.status}")
    }
}
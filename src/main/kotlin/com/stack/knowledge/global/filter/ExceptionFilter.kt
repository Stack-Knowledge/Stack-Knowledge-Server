package com.stack.knowledge.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.ErrorResponse
import com.stack.knowledge.global.error.exception.StackKnowledgeException
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter: OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            when (e) {
                is StackKnowledgeException -> {
                    log.error(e.message)
                    sendError(response, e.errorCode)
                }
                is Exception -> {
                    log.error(e.message)
                    sendError(response, ErrorCode.INTERNAL_SERVER_ERROR)
                }
            }
        }
    }

    private fun sendError(response: HttpServletResponse, errorCode: ErrorCode) {
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        val responseString = ObjectMapper().writeValueAsString(errorResponse)
        response.status = errorCode.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "utf-8"
        response.writer.write(responseString)
    }
}
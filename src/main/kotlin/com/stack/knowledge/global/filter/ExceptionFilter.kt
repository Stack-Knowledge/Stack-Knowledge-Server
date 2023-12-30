package com.stack.knowledge.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.stack.knowledge.global.error.ErrorCode
import com.stack.knowledge.global.error.ErrorResponse
import com.stack.knowledge.global.error.exception.StackKnowledgeException
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { e ->
            when (e) {
                is StackKnowledgeException -> sendError(response, e.errorCode)
                else -> {
                    println(e.message)
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
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(responseString)
    }
}
package com.stack.knowledege.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.stack.knowledege.global.error.ErrorCode
import com.stack.knowledege.global.error.ErrorResponse
import com.stack.knowledege.global.error.exception.BasicException
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            when (e) {
                is BasicException -> sendError(response, e.errorCode)
                is Exception -> sendError(response, ErrorCode.INTERNAL_SERVER_ERROR)
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
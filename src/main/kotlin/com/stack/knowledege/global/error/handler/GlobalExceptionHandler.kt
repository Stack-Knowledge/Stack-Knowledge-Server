package com.stack.knowledege.global.error.handler

import com.stack.knowledege.global.error.ErrorResponse
import com.stack.knowledege.global.error.exception.StackKnowledgeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(StackKnowledgeException::class)
    fun stackKnowledgeExceptionHandler(e: StackKnowledgeException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(message = e.errorCode.message, status = e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )
}
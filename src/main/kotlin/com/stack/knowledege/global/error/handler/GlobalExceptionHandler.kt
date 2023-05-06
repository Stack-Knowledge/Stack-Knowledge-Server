package com.stack.knowledege.global.error.handler

import com.stack.knowledege.global.error.ErrorResponse
import com.stack.knowledege.global.error.exception.BasicException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BasicException::class)
    fun BasicExceptionHandler(e: BasicException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        return ResponseEntity(
            ErrorResponse(message = errorCode.message, status = errorCode.status),
            HttpStatus.valueOf(errorCode.status)
        )
    }
}
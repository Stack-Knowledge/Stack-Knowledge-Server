package com.stack.knowledge.global.error.handler

import com.stack.knowledge.global.error.ErrorResponse
import com.stack.knowledge.global.error.exception.StackKnowledgeException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(StackKnowledgeException::class)
    fun stackKnowledgeExceptionHandler(e: StackKnowledgeException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.valueOf(e.errorCode.status)).body(ErrorResponse(message = e.errorCode.message, status = e.errorCode.status))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ErrorResponse(e.bindingResult.fieldError?.defaultMessage.toString(), HttpStatus.BAD_REQUEST.value()))

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException) : ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value())).body(ErrorResponse("json 형식이 잘못되었습니다.", HttpStatus.BAD_REQUEST.value()))
}
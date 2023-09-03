package com.stack.knowledge.domain.image.application.validator

import org.springframework.web.multipart.MultipartFile

interface ImageValidator {
    fun validateImageExtension(multipartFile: MultipartFile): String
}
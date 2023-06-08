package com.stack.knowledege.domain.image.application.spi

import org.springframework.web.multipart.MultipartFile

interface CommandImagePort {
    fun upload(multipartFile: MultipartFile, fileName: String): String
    fun deleteImageUrl(fileName: String)
}
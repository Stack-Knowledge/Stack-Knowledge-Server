package com.stack.knowledege.domain.image.application.spi

import org.springframework.web.multipart.MultipartFile

interface UploadImagePort {
    fun upload(multipartFile: MultipartFile, fileName: String): String
    fun getImageUrl(fileName: String): String
}
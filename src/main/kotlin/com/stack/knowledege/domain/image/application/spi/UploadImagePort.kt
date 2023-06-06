package com.stack.knowledege.domain.image.application.spi

import org.springframework.web.multipart.MultipartFile

interface UploadImagePort {
    fun upload(multipartFile: MultipartFile)
    fun getImageUrl(fileName: String)
}
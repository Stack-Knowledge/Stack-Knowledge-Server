package com.stack.knowledege.domain.image.application.usecase

import com.stack.knowledege.domain.image.application.spi.UploadImagePort
import com.stack.knowledege.domain.image.exception.MisMatchImageExtensionException
import com.stack.knowledege.global.annotation.usecase.UseCase
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val uploadImagePort: UploadImagePort
) {
    fun execute(multipartFile: MultipartFile) {
        val extension = multipartFile.name.substring(multipartFile.name.lastIndexOf(".")).lowercase()

        if (extension !in listOf("jpg", "jpeg", "png")) {
            throw MisMatchImageExtensionException()
        }

        val fileName = UUID.randomUUID().toString() + extension

        uploadImagePort.upload(multipartFile, fileName)
    }
}
package com.stack.knowledege.domain.image.application.usecase

import com.stack.knowledege.domain.image.application.spi.CommandImagePort
import com.stack.knowledege.domain.image.exception.MisMatchImageExtensionException
import com.stack.knowledege.global.annotation.usecase.UseCase
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort
) {
    fun execute(multipartFile: MultipartFile): String {
        val extension = multipartFile.originalFilename!!.substring(multipartFile.originalFilename!!.lastIndexOf(".")).lowercase()

        if (extension !in listOf(".jpg", ".jpeg", ".png")) {
            throw MisMatchImageExtensionException()
        }

        val fileName = UUID.randomUUID().toString() + extension

        return commandImagePort.upload(multipartFile, fileName)
    }
}
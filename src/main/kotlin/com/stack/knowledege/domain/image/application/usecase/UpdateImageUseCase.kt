package com.stack.knowledege.domain.image.application.usecase

import com.stack.knowledege.domain.image.application.spi.CommandImagePort
import com.stack.knowledege.domain.image.exception.MisMatchImageExtensionException
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UpdateImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(multipartFile: MultipartFile): String {
        val user = queryUserPort.queryCurrentUser()

        user.profileImage?.let { commandImagePort.deleteImageUrl(it) }

        val extension = multipartFile.originalFilename!!.substring(multipartFile.originalFilename!!.lastIndexOf(".")).lowercase()

        if (extension !in listOf(".jpg", ".jpeg", ".png")) {
            throw MisMatchImageExtensionException()
        }

        val fileName = UUID.randomUUID().toString() + extension

        return commandImagePort.upload(multipartFile, fileName)
    }
}
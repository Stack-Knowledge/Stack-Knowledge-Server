package com.stack.knowledege.domain.image.application.usecase

import com.stack.knowledege.domain.image.application.spi.CommandImagePort
import com.stack.knowledege.domain.image.exception.MisMatchImageExtensionException
import com.stack.knowledege.domain.image.exception.ProfileImageAlreadyExist
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.global.annotation.usecase.UseCase
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val userPort: UserPort
) {
    fun execute(multipartFile: MultipartFile): String {
        val user = userPort.queryCurrentUser()
        if (user.profileImage != null) {
            throw ProfileImageAlreadyExist()
        }

        val extension = multipartFile.originalFilename!!.substring(multipartFile.originalFilename!!.lastIndexOf(".")).lowercase()

        if (extension !in listOf(".jpg", ".jpeg", ".png")) {
            throw MisMatchImageExtensionException()
        }

        val fileName = UUID.randomUUID().toString() + extension

        userPort.save(user.copy(profileImage = fileName))
        return commandImagePort.upload(multipartFile, fileName)
    }
}
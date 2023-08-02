package com.stack.knowledege.domain.image.application.usecase

import com.stack.knowledege.domain.image.application.spi.CommandImagePort
import com.stack.knowledege.domain.image.application.validator.ImageValidator
import com.stack.knowledege.domain.image.exception.ProfileImageAlreadyExist
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.common.annotation.usecase.UseCase
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val userPort: UserPort,
    private val imageValidator: ImageValidator
) {
    fun execute(multipartFile: MultipartFile): String {
        val user = userPort.queryCurrentUser()

        if (user.profileImage != null) {
            throw ProfileImageAlreadyExist()
        }

        val fileName = imageValidator.validateImageExtension(multipartFile)
            .let { UUID.randomUUID().toString() + it }

        userPort.save(user.copy(profileImage = fileName))
        return commandImagePort.upload(multipartFile, fileName)
    }
}
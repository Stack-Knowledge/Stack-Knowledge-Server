package com.stack.knowledge.domain.image.application.usecase

import com.stack.knowledge.domain.image.application.spi.CommandImagePort
import com.stack.knowledge.domain.image.application.validator.ImageValidator
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.user.exception.ProfileImageNotFoundException
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val userPort: UserPort,
    private val imageValidator: ImageValidator,
    private val securityService: SecurityService
) {
    fun execute(image: MultipartFile): String {
        val user = securityService.queryCurrentUser()
        val profileImage = user.profileImage ?: throw ProfileImageNotFoundException()

        if (profileImage != "")
            commandImagePort.deleteImageUrl(profileImage)

        val fileName = imageValidator.validateImageExtension(image)
            .let { UUID.randomUUID().toString() + it }

        userPort.save(user.copy(profileImage = fileName))
        return commandImagePort.upload(image, fileName)
    }
}
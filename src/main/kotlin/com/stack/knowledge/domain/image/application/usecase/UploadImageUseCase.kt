package com.stack.knowledge.domain.image.application.usecase

import com.stack.knowledge.domain.image.application.spi.CommandImagePort
import com.stack.knowledge.domain.image.application.validator.ImageValidator
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.user.application.spi.CommandUserPort
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val commandUserPort: CommandUserPort,
    private val imageValidator: ImageValidator,
    private val securityService: SecurityService
) {
    fun execute(image: MultipartFile): String {
        val user = securityService.queryCurrentUser()

        if (!user.profileImage.isNullOrEmpty())
            commandImagePort.deleteImageUrl(user.profileImage)

        val fileName = imageValidator.validateImageExtension(image)
            .let { UUID.randomUUID().toString() + it }

        val profileImage = commandImagePort.upload(image, fileName)

        return commandUserPort.save(user.copy(profileImage = profileImage)).profileImage.toString()
    }
}
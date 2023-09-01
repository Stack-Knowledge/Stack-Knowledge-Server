package com.stack.knowledge.domain.image.application.usecase

import com.stack.knowledge.domain.image.application.spi.CommandImagePort
import com.stack.knowledge.domain.image.application.validator.ImageValidator
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.user.application.spi.CommandUserPort
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UpdateImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val commandUserPort: CommandUserPort,
    private val securityService: SecurityService,
    private val imageValidator: ImageValidator
) {
    fun execute(multipartFile: MultipartFile): String {
        val user = securityService.queryCurrentUser()

        user.profileImage?.let { commandImagePort.deleteImageUrl(it) }

        val fileName = imageValidator.validateImageExtension(multipartFile)
            .let { UUID.randomUUID().toString() + it }

        commandUserPort.save(user.copy(profileImage = fileName))

        return commandImagePort.upload(multipartFile, fileName)
    }
}
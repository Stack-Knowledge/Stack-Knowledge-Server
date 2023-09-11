package com.stack.knowledge.domain.image.application.usecase

import com.stack.knowledge.domain.image.application.spi.CommandImagePort
import com.stack.knowledge.domain.image.application.validator.ImageValidator
import com.stack.knowledge.domain.image.exception.ProfileImageAlreadyExist
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val userPort: UserPort,
    private val imageValidator: ImageValidator,
    private val securityService: SecurityService
) {
    fun execute(multipartFile: MultipartFile): String {
        val user = securityService.queryCurrentUser()

        if (user.profileImage != "")
            throw ProfileImageAlreadyExist()

        val fileName = imageValidator.validateImageExtension(multipartFile)
            .let { UUID.randomUUID().toString() + it }

        userPort.save(user.copy(profileImage = fileName))
        return commandImagePort.upload(multipartFile, fileName)
    }
}
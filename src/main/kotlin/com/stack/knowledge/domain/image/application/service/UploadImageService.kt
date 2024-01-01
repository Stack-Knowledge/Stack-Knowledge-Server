package com.stack.knowledge.domain.image.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.image.application.spi.CommandImagePort
import com.stack.knowledge.domain.image.exception.FileSizeTooSmallException
import com.stack.knowledge.domain.image.exception.MisMatchImageExtensionException
import com.stack.knowledge.domain.user.application.spi.CommandUserPort
import org.springframework.web.multipart.MultipartFile
import java.util.*

@ServiceWithTransaction
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val commandUserPort: CommandUserPort,
    private val securityService: SecurityService
) {
    fun execute(image: MultipartFile): String {
        val user = securityService.queryCurrentUser()

        if (image.size == 0.toLong())
            throw FileSizeTooSmallException()

        if (image.contentType !in listOf("image/jpeg", "image/png", "image/gif"))
            throw MisMatchImageExtensionException()

        if (!user.profileImage.isNullOrEmpty())
            commandImagePort.deleteImageUrl(user.profileImage)

        val profileImage = commandImagePort.upload(image, UUID.randomUUID().toString())

        return commandUserPort.save(user.copy(profileImage = profileImage)).profileImage.toString()
    }
}
package com.stack.knowledege.domain.image.application.usecase

import com.stack.knowledege.domain.image.application.spi.CommandImagePort
import com.stack.knowledege.domain.image.application.validator.ImageValidator
import com.stack.knowledege.domain.image.exception.ProfileImageAlreadyExist
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.common.service.SecurityService
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.security.exception.InvalidRoleException
import org.springframework.web.multipart.MultipartFile
import java.util.*

@UseCase
class UploadImageUseCase(
    private val commandImagePort: CommandImagePort,
    private val userPort: UserPort,
    private val imageValidator: ImageValidator,
    private val securityService: SecurityService,
    private val queryStudentPort: QueryStudentPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(multipartFile: MultipartFile): String {
        val user = when (securityService.queryCurrentUserAuthority()) {
            Authority.ROLE_STUDENT.name -> {
                val student = queryStudentPort.queryStudentById(securityService.queryCurrentUserId()) ?: throw UserNotFoundException()
                queryUserPort.queryUserById(student.user) ?: throw UserNotFoundException()
            }
            Authority.ROLE_TEACHER.name -> {
                queryUserPort.queryUserById(securityService.queryCurrentUserId()) ?: throw UserNotFoundException()
            }
            else -> throw InvalidRoleException()
        }

        if (user.profileImage != null)
            throw ProfileImageAlreadyExist()

        val fileName = imageValidator.validateImageExtension(multipartFile)
            .let { UUID.randomUUID().toString() + it }

        userPort.save(user.copy(profileImage = fileName))
        return commandImagePort.upload(multipartFile, fileName)
    }
}
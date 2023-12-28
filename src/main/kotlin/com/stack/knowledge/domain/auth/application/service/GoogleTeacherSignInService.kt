package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.GoogleService
import com.stack.knowledge.domain.auth.presentation.data.request.GoogleTeacherSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import java.util.*

@UseCase
class GoogleTeacherSignInService(
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort,
    private val googleService: GoogleService
) {
    fun execute(googleTeacherSignInRequest: GoogleTeacherSignInRequest): TokenResponse {
        val (email, name) = googleService.queryGoogleEmailAndName(googleTeacherSignInRequest.code)

        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = email,
                name = name,
                profileImage = "",
                authority = Authority.ROLE_TEACHER,
                approveStatus = ApproveStatus.PENDING
            )
        )

        return jwtGeneratorPort.receiveToken(user.id, Authority.ROLE_TEACHER)
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}
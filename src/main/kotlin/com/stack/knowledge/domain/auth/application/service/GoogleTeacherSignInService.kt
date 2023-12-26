package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import com.stack.knowledge.thirdparty.feign.client.GoogleAuthClient
import com.stack.knowledge.thirdparty.feign.client.GoogleInfoClient
import com.stack.knowledge.thirdparty.feign.dto.request.GoogleCodeRequest
import com.stack.knowledge.thirdparty.google.GoogleProperties
import java.util.*

@UseCase
class GoogleTeacherSignInService(
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient,
    private val googleProperties: GoogleProperties,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort
) {
    fun execute(code: String) {
        val googleCodeRequest = GoogleCodeRequest(
            code = code,
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            redirectUri = googleProperties.redirectUrl
        )

        val response = googleAuthClient.googleAuth(googleCodeRequest)

        val googleInfoResponse = googleInfoClient.googleInfo(response.accessToken)

        val email = googleInfoResponse.email
        val name = googleInfoResponse.name

        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = email,
                name = name,
                profileImage = null,
                authority = Authority.ROLE_TEACHER,
                approveStatus = ApproveStatus.PENDING
            )
        )

        jwtGeneratorPort.receiveToken(user.id, Authority.ROLE_TEACHER)
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}
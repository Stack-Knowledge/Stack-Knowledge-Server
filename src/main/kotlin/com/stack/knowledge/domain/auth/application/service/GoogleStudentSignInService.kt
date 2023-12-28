package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.domain.auth.exception.InvalidEmailException
import com.stack.knowledge.domain.auth.presentation.data.request.GoogleStudentSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
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
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.*

@UseCase
class GoogleStudentSignInService(
    private val googleAuthClient: GoogleAuthClient,
    private val googleInfoClient: GoogleInfoClient,
    private val googleProperties: GoogleProperties,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort
) {
    fun execute(googleStudentSignInRequest: GoogleStudentSignInRequest): TokenResponse {
        val googleCodeRequest = GoogleCodeRequest(
            code = URLDecoder.decode(googleStudentSignInRequest.code, StandardCharsets.UTF_8),
            clientId = googleProperties.clientId,
            clientSecret = googleProperties.clientSecret,
            redirectUri = googleProperties.redirectUrl
        )

        val response = googleAuthClient.googleAuth(googleCodeRequest)

        val googleInfoResponse = googleInfoClient.googleInfo(response.access_token)

        val email = googleInfoResponse.email
        val name = googleInfoResponse.name

        if (!email.matches(Regex("s2\\d0([0-6][0-9]|7[0-2])@gsm\\.hs\\.kr")))
            throw InvalidEmailException()

        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = email,
                name = name,
                profileImage = "",
                authority = Authority.ROLE_STUDENT,
                approveStatus = ApproveStatus.APPROVED
            )
        )

        return jwtGeneratorPort.receiveToken(user.id, Authority.ROLE_STUDENT)
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}
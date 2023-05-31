package com.stack.knowledege.domain.auth.application.usecase

import com.stack.knowledege.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.global.annotation.usecase.UseCase
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.thirdparty.gauth.spi.GAuthPort
import java.util.*

@UseCase
class GAuthSignInUseCase(
    private val gAuthPort: GAuthPort,
    private val userPort: UserPort,
    private val jwtGeneratorPort: JwtGeneratorPort
) {
    fun execute(gAuthSignInRequest: GAuthSignInRequest): TokenResponse {
        val gauthToken = gAuthPort.queryGAuthToken(gAuthSignInRequest.code)
        val gauthUserInfo = gAuthPort.queryUserInfo(gauthToken.accessToken)
        val role = userPort.queryUserRoleByEmail(gauthUserInfo.email, gauthUserInfo.role)

        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = gauthUserInfo.email,
                name = gauthUserInfo.name,
                roles = mutableListOf(role)
            )
        )

        return jwtGeneratorPort.receiveToken(user.email)
    }

    private fun createUser(user: User): User {
        println(user.roles.firstOrNull())
        return when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
    }
}
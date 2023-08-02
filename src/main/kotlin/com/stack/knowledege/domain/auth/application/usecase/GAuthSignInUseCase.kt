package com.stack.knowledege.domain.auth.application.usecase

import com.stack.knowledege.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.student.application.spi.QueryStudentPort
import com.stack.knowledege.domain.student.application.usecase.CreateStudentUseCase
import com.stack.knowledege.domain.student.exception.StudentNotFoundException
import com.stack.knowledege.domain.user.application.spi.UserPort
import com.stack.knowledege.domain.user.domain.User
import com.stack.knowledege.domain.user.domain.constant.Authority
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.common.annotation.usecase.UseCase
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.thirdparty.gauth.spi.GAuthPort
import java.util.*

@UseCase
class GAuthSignInUseCase(
    private val gAuthPort: GAuthPort,
    private val userPort: UserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val createStudentUseCase: CreateStudentUseCase,
    private val queryStudentPort: QueryStudentPort
) {
    fun execute(gAuthSignInRequest: GAuthSignInRequest): TokenResponse {

        val gAuthToken = gAuthPort.queryGAuthToken(gAuthSignInRequest.code)
        val gAuthUserInfo = gAuthPort.queryUserInfo(gAuthToken.accessToken)
        val authority = userPort.queryUserRoleByEmail(gAuthUserInfo.email, gAuthUserInfo.role)

        val user = createUser(
            User(
                id = UUID.randomUUID(),
                email = gAuthUserInfo.email,
                name = gAuthUserInfo.name,
                profileImage = "",
                authority = authority
            )
        )

        if (!queryStudentPort.existStudentByUser(user))
            createStudentUseCase.execute(user)

        val student = queryStudentPort.queryStudentByUser(user) ?: throw StudentNotFoundException()

        return when (user.authority) {
            Authority.ROLE_STUDENT -> jwtGeneratorPort.receiveToken(student.id, user.authority)
            Authority.ROLE_TEACHER -> jwtGeneratorPort.receiveToken(user.id, user.authority)
        }
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}
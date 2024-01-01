package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.domain.auth.application.spi.GAuthPort
import com.stack.knowledge.domain.auth.presentation.data.request.GAuthSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.domain.student.application.service.CreateStudentService
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import java.time.LocalDateTime
import java.util.*

@ServiceWithTransaction
class GAuthSignInService(
    private val gAuthPort: GAuthPort,
    private val userPort: UserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val createStudentService: CreateStudentService,
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
                authority = authority,
                approveStatus = ApproveStatus.APPROVED,
                createdAt = LocalDateTime.now()
            )
        )

        return when (authority) {
            Authority.ROLE_STUDENT -> {
                val studentId = if (!queryStudentPort.existStudentByUser(user)) {
                    createStudentService.execute(user).id
                } else {
                    queryStudentPort.queryStudentByUserId(user.id)?.id ?: throw UserNotFoundException()
                }
                jwtGeneratorPort.generateToken(studentId, authority)
            }
            Authority.ROLE_TEACHER -> jwtGeneratorPort.generateToken(user.id, authority)
        }
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}
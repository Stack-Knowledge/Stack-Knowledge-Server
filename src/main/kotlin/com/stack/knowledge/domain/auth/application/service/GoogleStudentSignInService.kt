package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.annotation.service.ServiceWithTransaction
import com.stack.knowledge.common.service.GoogleService
import com.stack.knowledge.domain.auth.exception.InvalidEmailException
import com.stack.knowledge.domain.auth.presentation.data.request.GoogleStudentSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.domain.student.application.service.CreateStudentService
import com.stack.knowledge.domain.student.application.spi.QueryStudentPort
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.UserNotFoundException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import java.util.*

@ServiceWithTransaction
class GoogleStudentSignInService(
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort,
    private val googleService: GoogleService,
    private val queryStudentPort: QueryStudentPort,
    private val createStudentService: CreateStudentService
) {
    fun execute(googleStudentSignInRequest: GoogleStudentSignInRequest): TokenResponse {
        val (email, name) = googleService.queryGoogleEmailAndName(googleStudentSignInRequest.code)

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
        val studentId = if (!queryStudentPort.existStudentByUser(user)) {
            createStudentService.execute(user).id
        } else {
            queryStudentPort.queryStudentByUserId(user.id)?.id ?: throw UserNotFoundException()
        }

        return jwtGeneratorPort.generateToken(studentId, Authority.ROLE_STUDENT)
    }

    private fun createUser(user: User): User =
        when (userPort.queryExistByEmail(user.email)) {
            true -> userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException()
            false -> userPort.save(user)
        }
}
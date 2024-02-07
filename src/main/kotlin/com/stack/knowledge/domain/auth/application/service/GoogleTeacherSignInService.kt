package com.stack.knowledge.domain.auth.application.service

import com.stack.knowledge.common.service.GoogleService
import com.stack.knowledge.domain.auth.exception.PendingUserException
import com.stack.knowledge.domain.auth.presentation.data.request.GoogleTeacherSignInRequest
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.domain.user.application.spi.UserPort
import com.stack.knowledge.domain.user.domain.User
import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.domain.constant.Authority
import com.stack.knowledge.domain.user.exception.RejectedUserException
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class GoogleTeacherSignInService(
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val userPort: UserPort,
    private val googleService: GoogleService
) {
    @Transactional(noRollbackFor = [PendingUserException::class], rollbackFor = [Exception::class])
    fun execute(googleTeacherSignInRequest: GoogleTeacherSignInRequest): TokenResponse {
        val (email, name) = googleService.queryGoogleEmailAndName(googleTeacherSignInRequest.code)

        val user = userPort.queryUserByEmail(email)

        return when (user?.approveStatus) {
            null -> {
                val user = User(
                    id = UUID(0, 0),
                    email = email,
                    name = name,
                    profileImage = "",
                    authority = Authority.ROLE_TEACHER,
                    approveStatus = ApproveStatus.PENDING
                )
                userPort.save(user)
                throw PendingUserException()
            }
            ApproveStatus.PENDING -> throw PendingUserException()
            ApproveStatus.APPROVED -> return jwtGeneratorPort.generateToken(user.id, Authority.ROLE_TEACHER)
            ApproveStatus.REJECTED -> throw RejectedUserException()
        }
    }
}
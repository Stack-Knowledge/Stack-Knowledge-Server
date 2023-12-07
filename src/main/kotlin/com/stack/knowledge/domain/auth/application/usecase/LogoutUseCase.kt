package com.stack.knowledge.domain.auth.application.usecase

import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.common.service.SecurityService
import com.stack.knowledge.domain.auth.application.spi.RefreshTokenPort
import com.stack.knowledge.domain.auth.exception.RefreshTokenNotFoundException
import com.stack.knowledge.domain.user.exception.UserNotFoundException

@UseCase
class LogoutUseCase(
    private val refreshTokenPort: RefreshTokenPort,
    private val securityService: SecurityService
) {
    fun execute(refreshToken: String) {
        val userId = securityService.queryCurrentUserId()

        val token = refreshTokenPort.queryById(refreshToken) ?: throw RefreshTokenNotFoundException()

        if (userId != token.userId)
            throw UserNotFoundException()

        refreshTokenPort.deleteRefreshTokenById(refreshToken)
    }
}
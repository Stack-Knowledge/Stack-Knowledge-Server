package com.stack.knowledege.domain.auth.application.usecase

import com.stack.knowledege.domain.auth.application.spi.QueryRefreshTokenPort
import com.stack.knowledege.domain.auth.exception.InvalidRefreshTokenException
import com.stack.knowledege.domain.auth.exception.RefreshTokenNotFoundException
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.common.annotation.usecase.UseCase
import com.stack.knowledege.common.service.SecurityService
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
    private val jwtParserPort: JwtParserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
    private val securityService: SecurityService
) {
    fun execute(requestToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(requestToken) ?: throw InvalidRefreshTokenException()
        val token = queryRefreshTokenPort.queryByRefreshToken(refreshToken) ?: throw RefreshTokenNotFoundException()
        val authority = securityService.queryCurrentUserAuthority()

        return jwtGeneratorPort.receiveToken(token.userId, authority)
    }
}
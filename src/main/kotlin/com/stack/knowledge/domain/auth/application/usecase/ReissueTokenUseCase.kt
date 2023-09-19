package com.stack.knowledge.domain.auth.application.usecase

import com.stack.knowledge.domain.auth.application.spi.QueryRefreshTokenPort
import com.stack.knowledge.domain.auth.exception.InvalidRefreshTokenException
import com.stack.knowledge.domain.auth.exception.RefreshTokenNotFoundException
import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.common.annotation.usecase.UseCase
import com.stack.knowledge.global.security.spi.JwtGeneratorPort
import com.stack.knowledge.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
    private val jwtParserPort: JwtParserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort
) {
    fun execute(requestToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(requestToken) ?: throw InvalidRefreshTokenException()
        val token = queryRefreshTokenPort.queryByRefreshToken(refreshToken) ?: throw RefreshTokenNotFoundException()

        return jwtGeneratorPort.receiveToken(token.userId, token.authority)
    }
}
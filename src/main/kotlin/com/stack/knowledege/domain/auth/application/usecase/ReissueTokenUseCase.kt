package com.stack.knowledege.domain.auth.application.usecase

import com.stack.knowledege.domain.auth.application.spi.QueryRefreshTokenPort
import com.stack.knowledege.domain.auth.exception.InvalidRefreshTokenException
import com.stack.knowledege.domain.auth.exception.RefreshTokenNotFoundException
import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.user.application.spi.QueryUserPort
import com.stack.knowledege.domain.user.exception.UserNotFoundException
import com.stack.knowledege.domain.common.annotation.usecase.UseCase
import com.stack.knowledege.global.security.spi.JwtGeneratorPort
import com.stack.knowledege.global.security.spi.JwtParserPort

@UseCase
class ReissueTokenUseCase(
    private val jwtParserPort: JwtParserPort,
    private val jwtGeneratorPort: JwtGeneratorPort,
    private val queryRefreshTokenPort: QueryRefreshTokenPort,
    private val queryUserPort: QueryUserPort
) {
    fun execute(refreshToken: String): TokenResponse {
        val refreshToken = jwtParserPort.parseRefreshToken(refreshToken) ?: throw InvalidRefreshTokenException()
        val token = queryRefreshTokenPort.queryByRefreshToken(refreshToken) ?: throw RefreshTokenNotFoundException()
        val user = queryUserPort.queryUserById(token.userId) ?: throw UserNotFoundException()

        return jwtGeneratorPort.receiveToken(token.userId, user.authority)
    }
}
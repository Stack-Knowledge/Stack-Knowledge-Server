package com.stack.knowledege.global.security.spi

import com.stack.knowledege.domain.user.presentation.data.response.TokenResponse

interface JwtGeneratorPort {
    fun receiveToken(email: String): TokenResponse
}
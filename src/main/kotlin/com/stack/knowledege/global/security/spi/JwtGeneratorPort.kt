package com.stack.knowledege.global.security.spi

import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse


interface JwtGeneratorPort {
    fun receiveToken(email: String): TokenResponse
}
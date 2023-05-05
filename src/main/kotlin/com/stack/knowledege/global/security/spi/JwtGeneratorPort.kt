package com.stack.knowledege.global.security.spi

import com.stack.knowledege.domain.user.adapter.presentation.data.response.TokenResponse

interface JwtGeneratorPort {
    fun receiveToken(email: String): TokenResponse
}
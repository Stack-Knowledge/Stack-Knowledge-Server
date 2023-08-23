package com.stack.knowledege.global.security.spi

import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import java.util.UUID

interface JwtGeneratorPort {
    fun receiveToken(userId: UUID, authority: String): TokenResponse
}
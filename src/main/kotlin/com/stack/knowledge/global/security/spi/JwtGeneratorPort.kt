package com.stack.knowledge.global.security.spi

import com.stack.knowledge.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledge.domain.user.domain.constant.Authority
import java.util.UUID

interface JwtGeneratorPort {
    fun receiveToken(userId: UUID, authority: Authority): TokenResponse
}
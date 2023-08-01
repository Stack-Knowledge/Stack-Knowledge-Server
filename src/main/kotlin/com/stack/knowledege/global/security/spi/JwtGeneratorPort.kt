package com.stack.knowledege.global.security.spi

import com.stack.knowledege.domain.auth.presentation.data.response.TokenResponse
import com.stack.knowledege.domain.user.domain.constant.Authority
import java.util.UUID

interface JwtGeneratorPort {
    fun receiveToken(userId: UUID, authority: Authority): TokenResponse
}
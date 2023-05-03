package com.stack.knowledege.domain.user.application.spi

import com.stack.knowledege.domain.user.adapter.presentation.data.response.TokenResponse

interface JwtPort {
    fun receiveToken(email: String): TokenResponse
}
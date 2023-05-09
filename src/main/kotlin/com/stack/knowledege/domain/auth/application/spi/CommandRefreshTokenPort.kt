package com.stack.knowledege.domain.auth.application.spi

import com.stack.knowledege.domain.auth.domain.RefreshToken

interface CommandRefreshTokenPort {
    fun saveRefreshToken(refreshToken: RefreshToken)
}
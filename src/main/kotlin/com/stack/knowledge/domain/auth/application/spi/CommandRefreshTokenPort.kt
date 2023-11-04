package com.stack.knowledge.domain.auth.application.spi

import com.stack.knowledge.domain.auth.domain.RefreshToken

interface CommandRefreshTokenPort {
    fun save(refreshToken: RefreshToken)
    fun deleteRefreshTokenById(refreshToken: String)
}
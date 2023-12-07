package com.stack.knowledge.domain.auth.application.spi

import com.stack.knowledge.domain.auth.domain.RefreshToken

interface QueryRefreshTokenPort {
    fun queryById(refreshToken: String): RefreshToken?
}
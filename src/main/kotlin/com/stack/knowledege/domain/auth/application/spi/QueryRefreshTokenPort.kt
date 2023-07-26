package com.stack.knowledege.domain.auth.application.spi

import com.stack.knowledege.domain.auth.domain.RefreshToken

interface QueryRefreshTokenPort {
    fun queryByRefreshToken(refreshToken: String): RefreshToken?
}
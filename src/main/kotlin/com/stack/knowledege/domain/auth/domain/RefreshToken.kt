package com.stack.knowledege.domain.auth.domain


data class RefreshToken(
    val email: String,
    val refreshToken: String,
    val expiredAt: Int
)
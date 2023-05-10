package com.stack.knowledege.domain.auth.domain

class RefreshToken(
    val email: String,
    val refreshToken: String,
    val expiredAt: Int
)
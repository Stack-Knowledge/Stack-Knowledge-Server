package com.stack.knowledege.domain.auth.domain

import com.stack.knowledege.global.annotation.Aggregate

@Aggregate
data class RefreshToken(
    val email: String,
    val refreshToken: String,
    val expiredAt: Int
)
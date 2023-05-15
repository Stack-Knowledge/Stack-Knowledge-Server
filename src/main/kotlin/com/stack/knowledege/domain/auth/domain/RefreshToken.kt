package com.stack.knowledege.domain.auth.domain

import com.stack.knowledege.global.annotation.Aggregate

@Aggregate
class RefreshToken(
    val email: String,
    val refreshToken: String,
    val expiredAt: Int
)
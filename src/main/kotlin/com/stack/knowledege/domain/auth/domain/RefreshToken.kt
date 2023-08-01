package com.stack.knowledege.domain.auth.domain

import com.stack.knowledege.global.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val userId: UUID,
    val expiredAt: Int
)
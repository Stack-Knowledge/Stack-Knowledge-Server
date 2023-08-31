package com.stack.knowledge.domain.auth.domain

import com.stack.knowledge.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val userId: UUID,
    val expiredAt: Int
)
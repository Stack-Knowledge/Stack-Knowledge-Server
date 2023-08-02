package com.stack.knowledege.domain.auth.domain

import com.stack.knowledege.domain.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val userId: UUID,
    val expiredAt: Int
)
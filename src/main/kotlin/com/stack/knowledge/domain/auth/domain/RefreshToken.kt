package com.stack.knowledge.domain.auth.domain

import com.stack.knowledge.common.annotation.Aggregate
import com.stack.knowledge.domain.user.domain.constant.Authority
import java.util.UUID

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val userId: UUID,
    val authority: Authority,
    val expiredAt: Int
)
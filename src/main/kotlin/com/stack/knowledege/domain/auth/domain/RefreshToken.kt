package com.stack.knowledege.domain.auth.domain

import com.stack.knowledege.common.annotation.Aggregate
import com.stack.knowledege.domain.user.domain.constant.Authority
import java.util.UUID

@Aggregate
data class RefreshToken(
    val refreshToken: String,
    val userId: UUID,
    val authority: Authority,
    val expiredAt: Int
)
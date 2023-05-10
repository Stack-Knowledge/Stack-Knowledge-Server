package com.stack.knowledege.domain.auth.persistence.entity

import com.stack.knowledege.global.security.token.JwtGeneratorAdapter
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "refreshToken", timeToLive = JwtGeneratorAdapter.REFRESH_EXP)
class RefreshTokenEntity(
    @Id
    val email: String,
    @Indexed
    val refreshToken: String,
    @TimeToLive
    val expiredAt: Int
)
package com.stack.knowledege.domain.auth.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed
import java.util.concurrent.TimeUnit

@RedisHash(value = "refreshToken")
data class RefreshTokenEntity(
    @Id
    val refreshToken: String,
    @Indexed
    val email: String,
    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int
)
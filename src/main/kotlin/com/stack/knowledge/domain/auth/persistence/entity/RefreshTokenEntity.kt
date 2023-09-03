package com.stack.knowledge.domain.auth.persistence.entity

import com.stack.knowledge.domain.user.domain.constant.Authority
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.UUID
import java.util.concurrent.TimeUnit

@RedisHash(value = "refreshToken")
data class RefreshTokenEntity(

    @Id
    val refreshToken: String,

    val userId: UUID,

    val authority: Authority,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int

)
package com.stack.knowledge.domain.auth.persistence.mapper

import com.stack.knowledge.domain.auth.domain.RefreshToken
import com.stack.knowledge.domain.auth.persistence.entity.RefreshTokenEntity
import com.stack.knowledge.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class RefreshTokenMapper : GenericMapper<RefreshToken, RefreshTokenEntity> {
    override fun toDomain(entity: RefreshTokenEntity?): RefreshToken? =
        entity?.let {
            RefreshToken(
                refreshToken = it.refreshToken,
                userId = it.userId,
                authority = it.authority,
                expiredAt = it.expiredAt
            )
        }

    override fun toEntity(domain: RefreshToken): RefreshTokenEntity =
        domain.let {
            RefreshTokenEntity(
                refreshToken = it.refreshToken,
                userId = it.userId,
                authority = it.authority,
                expiredAt = it.expiredAt
            )
        }
}
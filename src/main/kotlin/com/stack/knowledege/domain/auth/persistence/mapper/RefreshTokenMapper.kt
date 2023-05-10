package com.stack.knowledege.domain.auth.persistence.mapper

import com.stack.knowledege.domain.auth.domain.RefreshToken
import com.stack.knowledege.domain.auth.persistence.entity.RefreshTokenEntity
import com.stack.knowledege.global.mapper.GenericMapper
import org.springframework.stereotype.Component

@Component
class RefreshTokenMapper() : GenericMapper<RefreshToken, RefreshTokenEntity> {
    override fun toDomain(entity: RefreshTokenEntity?): RefreshToken? =
        entity?.let {
            RefreshToken(
                email = it.email,
                refreshToken = it.refreshToken,
                expiredAt = it.expiredAt
            )
        }

    override fun toEntity(domain: RefreshToken): RefreshTokenEntity =
        domain?.let {
            RefreshTokenEntity(
                email = it.email,
                refreshToken = it.refreshToken,
                expiredAt = it.expiredAt
            )
        }
}
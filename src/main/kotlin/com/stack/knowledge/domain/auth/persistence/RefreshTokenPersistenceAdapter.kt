package com.stack.knowledge.domain.auth.persistence

import com.stack.knowledge.domain.auth.application.spi.RefreshTokenPort
import com.stack.knowledge.domain.auth.domain.RefreshToken
import com.stack.knowledge.domain.auth.persistence.mapper.RefreshTokenMapper
import com.stack.knowledge.domain.auth.persistence.repository.RefreshTokenRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenPort {

    override fun save(refreshToken: RefreshToken) {
        refreshTokenRepository.save(refreshTokenMapper.toEntity(refreshToken))
    }

    override fun queryByRefreshToken(refreshToken: String): RefreshToken? =
        refreshTokenMapper.toDomain(refreshTokenRepository.findByIdOrNull(refreshToken))
}
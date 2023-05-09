package com.stack.knowledege.domain.auth.persistence

import com.stack.knowledege.domain.auth.application.spi.RefreshTokenPort
import com.stack.knowledege.domain.auth.domain.RefreshToken
import com.stack.knowledege.domain.auth.persistence.mapper.RefreshTokenMapper
import com.stack.knowledege.domain.auth.persistence.repository.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class RefreshTokenPersistence(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenMapper: RefreshTokenMapper
) : RefreshTokenPort {
    override fun saveRefreshToken(refreshToken: RefreshToken) {
        refreshTokenRepository.save(refreshTokenMapper.toEntity(refreshToken))
    }

}
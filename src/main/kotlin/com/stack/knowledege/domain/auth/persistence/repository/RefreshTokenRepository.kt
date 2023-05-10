package com.stack.knowledege.domain.auth.persistence.repository

import com.stack.knowledege.domain.auth.persistence.entity.RefreshTokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository: JpaRepository<RefreshTokenEntity, String>
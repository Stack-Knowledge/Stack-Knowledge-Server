package com.stack.knowledge.domain.auth.persistence.repository

import com.stack.knowledge.domain.auth.persistence.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String>
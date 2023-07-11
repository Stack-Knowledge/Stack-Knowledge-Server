package com.stack.knowledege.domain.auth.persistence.repository

import com.stack.knowledege.domain.auth.persistence.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshTokenEntity, String>
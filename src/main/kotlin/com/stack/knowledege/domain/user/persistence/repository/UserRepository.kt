package com.stack.knowledege.domain.user.persistence.repository

import com.stack.knowledege.domain.user.persistence.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
}
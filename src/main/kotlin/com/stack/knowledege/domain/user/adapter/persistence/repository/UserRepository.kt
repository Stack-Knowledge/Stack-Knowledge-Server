package com.stack.knowledege.domain.user.adapter.persistence.repository

import com.stack.knowledege.domain.user.adapter.persistence.entity.UserEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}
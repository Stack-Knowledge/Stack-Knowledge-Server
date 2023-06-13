package com.stack.knowledege.domain.user.persistence.repository

import com.stack.knowledege.domain.user.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
    fun existsByEmail(email: String): Boolean
    fun findAllUserPointDesc(): List<Int>
}
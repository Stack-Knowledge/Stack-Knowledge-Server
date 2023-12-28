package com.stack.knowledge.domain.user.persistence.repository

import com.stack.knowledge.domain.user.domain.constant.ApproveStatus
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserJpaRepository : CrudRepository<UserJpaEntity, UUID> {
    fun findByEmail(email: String): UserJpaEntity?
    fun existsByEmail(email: String): Boolean
    fun findAllByApproveStatus(approveStatus: ApproveStatus): List<UserJpaEntity>
}
package com.stack.knowledge.domain.student.persistence.repository

import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledge.domain.user.persistence.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface StudentJpaRepository : CrudRepository<StudentJpaEntity, UUID> {
    fun findAllByOrderByCumulatePointDesc(): List<StudentJpaEntity>
    fun findByUserId(userId: UUID): StudentJpaEntity
    fun existsByUser(userJpaEntity: UserJpaEntity): Boolean
}
package com.stack.knowledege.domain.student.persistence.repository

import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import com.stack.knowledege.domain.user.persistence.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface StudentJpaRepository : CrudRepository<StudentJpaEntity, UUID> {
    fun findAllByOrderByPointDesc(): List<StudentJpaEntity>
    fun findByUser(userJpaEntity: UserJpaEntity): StudentJpaEntity
    fun existsByUser(userJpaEntity: UserJpaEntity): Boolean
}
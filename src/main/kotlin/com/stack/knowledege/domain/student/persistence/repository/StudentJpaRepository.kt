package com.stack.knowledege.domain.student.persistence.repository

import com.stack.knowledege.domain.student.persistence.entity.StudentJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface StudentJpaRepository : CrudRepository<StudentJpaEntity, UUID> {
    fun findAllByOrderByPointDesc(): List<StudentJpaEntity>
}
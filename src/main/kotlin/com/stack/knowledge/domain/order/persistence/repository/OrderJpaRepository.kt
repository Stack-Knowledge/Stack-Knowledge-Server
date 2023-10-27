package com.stack.knowledge.domain.order.persistence.repository

import com.stack.knowledge.domain.order.persistence.entity.OrderJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OrderJpaRepository : CrudRepository<OrderJpaEntity, UUID> {
    fun findAllByStudent(studentJpaEntity: StudentJpaEntity): List<OrderJpaEntity>
    fun findAllByOrderByCreatedAt(): List<OrderJpaEntity>
}
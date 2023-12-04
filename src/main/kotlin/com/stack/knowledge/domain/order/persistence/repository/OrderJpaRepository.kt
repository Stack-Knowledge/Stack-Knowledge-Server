package com.stack.knowledge.domain.order.persistence.repository

import com.stack.knowledge.domain.order.persistence.entity.OrderJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface OrderJpaRepository : CrudRepository<OrderJpaEntity, UUID> {
    fun findAllByStudent(student: StudentJpaEntity): List<OrderJpaEntity>
    fun findAllByOrderByCreatedAtDesc(): List<OrderJpaEntity>
}
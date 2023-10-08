package com.stack.knowledge.domain.order.persistence.repository

import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.order.persistence.entity.OrderJpaEntity
import com.stack.knowledge.domain.student.persistence.entity.StudentJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OrderJpaRepository : CrudRepository<OrderJpaEntity, UUID> {
    fun findAllByOrOrderStatus(orderStatus: OrderStatus): List<OrderJpaEntity>
    fun findAllByOrderStatusAndStudent(orderStatus: OrderStatus, studentJpaEntity: StudentJpaEntity): List<OrderJpaEntity>
}
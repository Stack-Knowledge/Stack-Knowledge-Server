package com.stack.knowledge.domain.order.persistence.repository

import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.order.persistence.entity.OrderJpaEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface OrderJpaRepository : CrudRepository<OrderJpaEntity, UUID> {
    fun findByOrderStatus(orderStatus: OrderStatus): List<OrderJpaEntity>
}
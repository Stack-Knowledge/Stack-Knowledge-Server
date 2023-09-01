package com.stack.knowledge.domain.order.persistence

import com.stack.knowledge.domain.order.application.spi.OrderPort
import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import com.stack.knowledge.domain.order.persistence.mapper.OrderMapper
import com.stack.knowledge.domain.order.persistence.repository.OrderJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository,
    private val orderMapper: OrderMapper
) : OrderPort {
    override fun save(order: Order) {
        orderJpaRepository.save(orderMapper.toEntity(order))
    }

    override fun queryOrderById(orderId: UUID): Order? =
        orderMapper.toDomain(orderJpaRepository.findByIdOrNull(orderId))

    override fun queryAllIsOrderedItem(orderStatus: OrderStatus): List<Order> =
        orderJpaRepository.findByOrderStatus(orderStatus).map { orderMapper.toDomain(it)!! }
}
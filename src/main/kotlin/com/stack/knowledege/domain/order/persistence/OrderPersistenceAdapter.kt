package com.stack.knowledege.domain.order.persistence

import com.stack.knowledege.domain.order.application.spi.OrderPort
import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.order.persistence.mapper.OrderMapper
import com.stack.knowledege.domain.order.persistence.repository.OrderJpaRepository
import org.springframework.stereotype.Component

@Component
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository,
    private val orderMapper: OrderMapper
) : OrderPort {
    override fun save(order: Order) {
        orderJpaRepository.save(orderMapper.toEntity(order))
    }

    override fun queryAllOder(): List<Order> =
        orderJpaRepository.findAll().map { orderMapper.toDomain(it)!! }

    override fun queryAllIsOrderedItem(orderStatus: OrderStatus): List<Order> =
        orderJpaRepository.findByOrderStatus(orderStatus).map { orderMapper.toDomain(it)!! }
}
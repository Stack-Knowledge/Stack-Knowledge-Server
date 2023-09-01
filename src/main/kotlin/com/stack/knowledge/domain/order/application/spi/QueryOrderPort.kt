package com.stack.knowledge.domain.order.application.spi

import com.stack.knowledge.domain.order.domain.Order
import com.stack.knowledge.domain.order.domain.constant.OrderStatus
import java.util.UUID

interface QueryOrderPort {
    fun queryOrderById(orderId: UUID): Order?
    fun queryAllIsOrderedItem(orderStatus: OrderStatus): List<Order>
}
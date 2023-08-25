package com.stack.knowledege.domain.order.application.spi

import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import java.util.UUID

interface QueryOrderPort {
    fun queryOrderById(orderId: UUID): Order?
    fun queryAllIsOrderedItem(orderStatus: OrderStatus): List<Order>
}
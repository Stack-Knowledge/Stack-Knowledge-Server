package com.stack.knowledege.domain.order.application.spi

import com.stack.knowledege.domain.order.domain.Order
import com.stack.knowledege.domain.order.domain.constant.OrderStatus

interface QueryOrderPort {
    fun queryAllOder(): List<Order>
    fun queryAllIsOrderedItem(orderStatus: OrderStatus): List<Order>
}
package com.stack.knowledege.domain.order.application.spi

import com.stack.knowledege.domain.order.domain.Order

interface QueryOrderPort {
    fun queryAllOder(): List<Order>
}
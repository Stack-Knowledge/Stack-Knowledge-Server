package com.stack.knowledge.domain.order.application.spi

import com.stack.knowledge.domain.order.domain.Order

interface CommandOrderPort {
    fun save(order: Order)
}
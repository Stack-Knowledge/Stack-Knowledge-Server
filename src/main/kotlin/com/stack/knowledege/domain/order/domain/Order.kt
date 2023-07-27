package com.stack.knowledege.domain.order.domain

import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import java.util.UUID

data class Order(
    val id: UUID,
    val count: Int,
    val price: Int,
    val orderStatus: OrderStatus,
    val itemId: UUID,
    val studentId: UUID
)
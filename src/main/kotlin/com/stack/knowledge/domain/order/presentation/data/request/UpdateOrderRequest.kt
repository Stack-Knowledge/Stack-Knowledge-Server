package com.stack.knowledge.domain.order.presentation.data.request

import java.util.*

data class UpdateOrderRequest(
    val orderId: UUID,
    val count: Int
)
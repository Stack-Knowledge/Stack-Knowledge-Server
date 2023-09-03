package com.stack.knowledge.domain.order.presentation.data.request

import java.util.*

data class UpdateOrderStatusRequest(
    val orderId: UUID,
    val count: Int
)
package com.stack.knowledge.domain.order.presentation.data.request

import java.util.*

data class OrderItemRequest(
    val itemId: UUID,
    val count: Int
)
package com.stack.knowledege.domain.order.presentation.data.request

import java.util.UUID

data class OrderItemRequest(
    val itemId: UUID,
    val count: Int
)
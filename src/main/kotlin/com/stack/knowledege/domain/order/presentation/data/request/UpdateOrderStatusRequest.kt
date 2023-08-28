package com.stack.knowledege.domain.order.presentation.data.request

import java.util.*

data class UpdateOrderStatusRequest(
    val orderId: UUID,
    val count: Int
)
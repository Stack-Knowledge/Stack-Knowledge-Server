package com.stack.knowledge.domain.order.presentation.data.request

import java.util.*
import javax.validation.constraints.NotNull

data class UpdateOrderRequest(
    @field:NotNull
    val orderId: UUID,
    @field:NotNull
    val count: Int
)
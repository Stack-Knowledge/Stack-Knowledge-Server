package com.stack.knowledge.domain.order.presentation.data.request

import java.util.*
import javax.validation.constraints.NotNull

data class OrderItemRequest(
    @field:NotNull
    val itemId: UUID,
    @field:NotNull
    val count: Int
)
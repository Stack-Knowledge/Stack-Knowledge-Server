package com.stack.knowledge.domain.order.presentation.data.response

import com.stack.knowledge.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledge.domain.user.presentation.data.response.UserResponse
import java.util.*

class IsOrderedOrderResponse(
    val id: UUID,
    val count: Int,
    val price: Int,
    val item: ItemResponse,
    val user: UserResponse
)
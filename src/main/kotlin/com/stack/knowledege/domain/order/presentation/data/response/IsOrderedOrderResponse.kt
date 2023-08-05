package com.stack.knowledege.domain.order.presentation.data.response

import com.stack.knowledege.domain.item.presentation.data.response.ItemResponse
import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import com.stack.knowledege.domain.user.presentation.data.response.UserResponse
import java.util.*

class IsOrderedOrderResponse(
    val id: UUID,
    val count: Int,
    val price: Int,
    val orderStatus: OrderStatus,
    val item: ItemResponse,
    val user: UserResponse
)
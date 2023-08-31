package com.stack.knowledge.domain.order.presentation.data.request

import com.stack.knowledge.domain.order.domain.constant.OrderStatus

class UpdateOrderStatusRequest(
    val orderStatus: OrderStatus
)
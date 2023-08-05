package com.stack.knowledege.domain.order.presentation.data.request

import com.stack.knowledege.domain.order.domain.constant.OrderStatus

class UpdateOrderStatusRequest(
    val orderStatus: OrderStatus
)
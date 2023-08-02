package com.stack.knowledege.domain.order.presentation.data.request

import com.stack.knowledege.domain.order.domain.constant.OrderStatus
import javax.validation.constraints.NotBlank

class UpdateOrderStatusRequest(
    @field:NotBlank
    val orderStatus: OrderStatus
)
package com.stack.knowledege.domain.order.presentation.data.request

import javax.validation.constraints.NotBlank

data class OrderItemRequest(
    @field:NotBlank
    val count: Int
)
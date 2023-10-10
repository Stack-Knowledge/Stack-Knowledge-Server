package com.stack.knowledge.domain.order.domain

import java.util.UUID

data class Order(
    val id: UUID,
    val count: Int,
    val price: Int,
    val itemId: UUID,
    val studentId: UUID
)
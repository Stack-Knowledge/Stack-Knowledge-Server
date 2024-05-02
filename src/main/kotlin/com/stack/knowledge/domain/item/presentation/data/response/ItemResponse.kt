package com.stack.knowledge.domain.item.presentation.data.response

import java.util.*

data class ItemResponse(
    val itemId: UUID,
    val name: String,
    val price: Int,
    val image: String
) {
    constructor(): this(UUID(0, 0), "", 0, "")
}
package com.stack.knowledge.domain.item.presentation.data.response

import java.util.*

data class ItemResponse(
    val id: UUID,
    val name: String,
    val price: Int
)
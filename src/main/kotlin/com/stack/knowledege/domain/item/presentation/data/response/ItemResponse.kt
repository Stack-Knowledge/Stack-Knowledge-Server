package com.stack.knowledege.domain.item.presentation.data.response

import java.util.*

data class ItemResponse(
    val itemId: UUID,
    val name: String,
    val text: String,
    val price: Int
)